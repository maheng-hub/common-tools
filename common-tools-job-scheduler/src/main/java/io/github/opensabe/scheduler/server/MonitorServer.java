package io.github.opensabe.scheduler.server;

import io.github.opensabe.scheduler.conf.JobStatus;
import io.github.opensabe.scheduler.conf.SchedulerServerConfiguration;
import io.github.opensabe.scheduler.job.SchedulerJob;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log4j2
public class MonitorServer implements Runnable {

    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
    private final Map<String, SchedulerJob> jobs;

    public MonitorServer(RedissonClient redissonClient, StringRedisTemplate stringRedisTemplate, Map<String, SchedulerJob> jobs) {
        this.redissonClient = redissonClient;
        this.stringRedisTemplate = stringRedisTemplate;
        this.jobs = jobs;
    }

    @Override
    public void run() {
        log.info("Monitor server is checking...");
        RLock lock = redissonClient.getLock(SchedulerServerConfiguration.REDIS_JOB_MONITOR_KEY + ":checking");
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock(0L, TimeUnit.SECONDS);
            if (!isLocked) {
                log.info("Monitor server cannot get the lock");
                return;
            }

            stringRedisTemplate.<String,Object>opsForHash().entries(SchedulerServerConfiguration.REDIS_JOB_MONITOR_KEY).forEach((k, v) -> {
                try {
                    long expiredTime = Long.parseLong(v.toString());
                    if (expiredTime - System.currentTimeMillis() <= 0) {
                        stringRedisTemplate.opsForHash().delete(SchedulerServerConfiguration.REDIS_JOB_MONITOR_KEY, k);
                        if (jobs.containsKey(k)){
                            log.info("Update job {} status as FINISH", k);
                            jobs.get(k).setStatus(JobStatus.FINISHED);
                        }
                    }
                } catch (Throwable ex) {
                    log.error("Processing job {} status exception", k, ex);
                }
            });

            stringRedisTemplate.<String,Object>opsForHash().entries(SchedulerServerConfiguration.REDIS_JOB_MISFIRE_KEY).forEach((k, v) -> {
                try {
                    long expiredTime = Long.parseLong(v.toString());
                    if (expiredTime - System.currentTimeMillis() <= 0) {
                        stringRedisTemplate.opsForHash().delete(SchedulerServerConfiguration.REDIS_JOB_MISFIRE_KEY, k);
                        if (jobs.containsKey(k)){
                            log.info("Update misfire job {} status as FINISH", k);
                            jobs.get(k).setStatus(JobStatus.FINISHED);
                        }
                    }
                } catch (Throwable ex) {
                    log.error("Processing misfire job {} status exception", k, ex);
                }
            });
        } catch (Throwable e) {
            log.error(e);
        } finally {
            if (isLocked) {
                lock.unlock();
            }
        }
    }
}
