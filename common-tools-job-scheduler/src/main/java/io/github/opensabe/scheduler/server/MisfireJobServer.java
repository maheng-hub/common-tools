package io.github.opensabe.scheduler.server;

import io.github.opensabe.scheduler.conf.SchedulerServerConfiguration;
import io.github.opensabe.scheduler.job.MisfireJobRunner;
import io.github.opensabe.scheduler.job.SchedulerJob;
import io.github.opensabe.scheduler.listener.JobListeners;
import io.github.opensabe.scheduler.utils.MisfireQueue;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Log4j2
public class MisfireJobServer implements Runnable {

    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
    private final JobListeners jobListeners;
    private final Long expiredTime;

    public MisfireJobServer(RedissonClient redissonClient, StringRedisTemplate stringRedisTemplate, JobListeners jobListeners, Long expiredTime) {
        this.redissonClient = redissonClient;
        this.stringRedisTemplate = stringRedisTemplate;
        this.jobListeners = jobListeners;
        this.expiredTime = expiredTime;
    }

    @Override
    public void run() {
        log.info("Misfire job server is checking...");
        RLock lock = redissonClient.getLock(SchedulerServerConfiguration.REDIS_JOB_MISFIRE_KEY + ":checking");
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock(0L, TimeUnit.SECONDS);
            if (!isLocked) {
                log.info("Misfire job server cannot get the lock");
                return;
            }

            if (MisfireQueue.isEmpty()) {
                log.info("Misfire queue is empty");
                return;
            }

            while (!MisfireQueue.isEmpty()) {
                SchedulerJob job = MisfireQueue.nextJob();
                log.info("Misfire job {} generate a new thread and ready to go", job.getJobName());
                stringRedisTemplate.opsForList().rightPush(SchedulerServerConfiguration.REDIS_JOB_MISFIRE_QUEUE_KEY, job.getJobId());
                new MisfireJobRunner(job, jobListeners, redissonClient, stringRedisTemplate, expiredTime).start();
            }
        } catch (Throwable e) {
            log.error("Misfire server processing exception ", e);
        } finally {
            if (isLocked) {
                lock.unlock();
            }
        }
    }
}
