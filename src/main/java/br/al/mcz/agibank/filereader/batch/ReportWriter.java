package br.al.mcz.agibank.filereader.batch;

import br.al.mcz.agibank.filereader.entities.AnaliseDados;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class ReportWriter implements Tasklet, StepExecutionListener {
    private AnaliseDados analiseDados;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.analiseDados = (AnaliseDados) executionContext.get("analiseDados");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        //TODO montar file de relatório
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        //TODO salvar arquivo
        return ExitStatus.COMPLETED;
    }

}
