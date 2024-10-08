package io.github.opensabe.scheduler.job;

import io.github.opensabe.scheduler.conf.JobStatus;
import io.github.opensabe.scheduler.conf.SchedulerServerConfiguration;
import io.github.opensabe.scheduler.utils.MisfireQueue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

@Log4j2
@NoArgsConstructor
@AllArgsConstructor
public class MisfireJobExecutor implements Runnable {

    private RedissonClient redissonClient;
    private SchedulerJob schedulerJob;

    @Override
    public void run() {
        log.info("Misfire job executor is processing...");
        try {
            TimeUnit.MILLISECONDS.sleep(500L);
        } catch (InterruptedException ignore) {
        }

        RLock lock = redissonClient.getLock(SchedulerServerConfiguration.REDIS_JOB_STATUS_KEY + ":" + schedulerJob.getJobId());
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock(1L, 5L, TimeUnit.SECONDS);
            if (!isLocked) {
                log.info("Job {} cannot get lock", schedulerJob.getJobName());
                return;
            }
            log.info("Job {} status is {}", schedulerJob.getJobName(), schedulerJob.getStatus());
            if (schedulerJob.getStatus() == JobStatus.PROCESSING) {
                log.info("Job {} is still running", schedulerJob.getJobName());
                if (schedulerJob.isMisfire()) {
                    log.info("A misfire job {} is generated {}", schedulerJob.getJobName(), schedulerJob.getJobId());
                    MisfireQueue.enqueue(schedulerJob);
                }
            }
            if (schedulerJob.getStatus() == JobStatus.STARTED) {
                log.info("Update job {} status to {}", schedulerJob.getJobName(), JobStatus.PROCESSING);
                schedulerJob.setStatus(JobStatus.PROCESSING);
            }
        } catch (InterruptedException e) {
            log.error("Misfire job executor process job {} exception ", schedulerJob.getJobName(), e);
        } finally {
            if (isLocked) {
                lock.unlock();
            }
        }
    }
}
