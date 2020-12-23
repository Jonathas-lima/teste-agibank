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
    public Job job(LinesReader linesReader, LinesProcessor linesProcessor, ReportWriter linesWriter) {
        return jobBuilderFactory
                .get("taskletsJob")
                .start(readLines(linesReader))
                .next(processLines(linesProcessor))
                .next(writeLines(linesWriter))
                .build();
    }

    @Bean
    protected Step readLines(LinesReader linesReader) {
        return stepBuilderFactory
                .get("readLines")
                .tasklet(linesReader)
                .build();
    }

    @Bean
    protected Step processLines(LinesProcessor linesProcessor) {
        return stepBuilderFactory
                .get("processLines")
                .tasklet(linesProcessor)
                .build();
    }

    @Bean
    protected Step writeLines(ReportWriter linesWriter) {
        return stepBuilderFactory
                .get("writeLines")
                .tasklet(linesWriter)
                .build();
    }
}
