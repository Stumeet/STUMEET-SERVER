package com.stumeet.server.batch.runner;

import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobRunner {

    private final JobLauncher jobLauncher;
    private final JobLocator jobLocator;

    @Scheduled(cron = "0 0 9 * * ?")
    public void scheduleNotificationJob() {
        executeJob("memberScheduleNotification", Map.of());
    }

    @Scheduled(cron = "0 55 23 * * ?")
    public void scheduleGrapeStatusResetJob() {
        executeJob("GrapeStatusReset", Map.of());
    }

    public void executeJob(String jobName, Map<String, Object> params) {
        log.info("Starting the batch job: {}", jobName);
        try {
            Job job = jobLocator.getJob(jobName);
            JobParametersBuilder builder = new JobParametersBuilder();
            params.forEach((key, value) -> builder.addString(key, value.toString()));

            JobParameters jobParameters = new JobParametersBuilder()
                .addString("jobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

            final JobExecution execution = jobLauncher.run(job, jobParameters);
            log.info("Job {} executed with status: {}", jobName, execution.getStatus());
        } catch (final Exception e) {
            log.error("Failed to execute job {}: {}", jobName, e.getMessage(), e);
        }
    }
}