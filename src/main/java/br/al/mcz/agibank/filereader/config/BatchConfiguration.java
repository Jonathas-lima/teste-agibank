package br.al.mcz.agibank.filereader.config;

import br.al.mcz.agibank.filereader.batch.LinesProcessor;
import br.al.mcz.agibank.filereader.batch.LinesReader;
import br.al.mcz.agibank.filereader.batch.ReportWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableBatchProcessing
@EnableScheduling
@AllArgsConstructor
public class BatchConfiguration {


    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory
                .get("taskletsJob")
                .start(readLines())
                .next(processLines())
                .next(writeLines())
                .build();
    }

    @Bean
    protected Step readLines() {
        return stepBuilderFactory
                .get("readLines")
                .tasklet(linesReader())
                .build();
    }

    @Bean
    protected Step processLines() {
        return stepBuilderFactory
                .get("processLines")
                .tasklet(linesProcessor())
                .build();
    }

    @Bean
    protected Step writeLines() {
        return stepBuilderFactory
                .get("writeLines")
                .tasklet(linesWriter())
                .build();
    }

    @Bean
    public LinesReader linesReader(){
        return new LinesReader();
    }

    @Bean
    public LinesProcessor linesProcessor() {
        return new LinesProcessor();
    }

    @Bean
    public ReportWriter linesWriter(){
        return new ReportWriter();
    }
}
