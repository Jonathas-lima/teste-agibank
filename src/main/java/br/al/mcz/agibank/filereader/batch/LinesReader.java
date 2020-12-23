package br.al.mcz.agibank.filereader.batch;

import br.al.mcz.agibank.filereader.exceptions.ArquivoVazioException;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static br.al.mcz.agibank.filereader.util.FileUtil.readFiles;

public class LinesReader implements Tasklet, StepExecutionListener {
    @Value("${diretorio.entrada}")
    private String path;
    private List<String> lines;

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        lines = readFiles(path);
        validarDadosArquivo();
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("lines", this.lines);
        return ExitStatus.COMPLETED;
    }

    private void validarDadosArquivo() {
        if(this.lines.isEmpty()){
            throw new ArquivoVazioException();
        }
    }
}
