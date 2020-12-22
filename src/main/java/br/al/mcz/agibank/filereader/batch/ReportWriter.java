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
import org.springframework.beans.factory.annotation.Value;


import static br.al.mcz.agibank.filereader.util.FileUtil.writeFile;

public class ReportWriter implements Tasklet, StepExecutionListener {

    @Value("${diretorio.saida}")
    private String diretorioSaida;
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
        diretorioSaida = "/home/jonathas/homepath/data/out/output.dat";
        writeFile(diretorioSaida, "Relatório Análise dados", analiseDados.obterResumoAnalise());
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        //TODO salvar arquivo
        return ExitStatus.COMPLETED;
    }

}
