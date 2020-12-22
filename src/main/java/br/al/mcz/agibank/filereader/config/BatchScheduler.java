package br.al.mcz.agibank.filereader.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@AllArgsConstructor
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job job;

    @Scheduled(cron = "${intervaloBatch}", zone = "America/Maceio")
    private void runJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", ZonedDateTime.now().toString())
                .toJobParameters();
        this.jobLauncher.run(this.job, jobParameters);
    }
}
