package br.al.mcz.agibank.filereader.cucumber.stepdefs;

import br.al.mcz.agibank.filereader.config.BatchConfiguration;
import br.al.mcz.agibank.filereader.cucumber.SpringIntegrationTest;
import br.al.mcz.agibank.filereader.util.ThreadUtil;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;


public class AnaliseDadosStepdefs extends SpringIntegrationTest {

    @Autowired
    SimpleJobLauncher jobLauncher;

    @Autowired
    BatchConfiguration batchConfiguration;

    private Path pathFile;

    @Dado("^que exista um arquivo com os seguintes dados:$")
    public void queExistaUmArquivoComOsSeguintesDados(List<String> dados) {

        try {
            Path tempFile = Files.createTempFile("dados", ".dat");
            pathFile = Files.write(tempFile, dados, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Quando("quando executar a rotina de processamento")
    public void quandoExecutarARotinaDeProcessamento() throws Exception {

        JobParameters param = new JobParametersBuilder().addString("JobID", valueOf(currentTimeMillis())).toJobParameters();
        JobExecution job = jobLauncher.run(batchConfiguration.job(), param);
        ThreadUtil.esperarJob(job);
    }

    @Então("o sistema gerará um aquivo de resumo com os seguintes dados:")
    public void oSistemaGeraraUmAquivoDeResumoComOsSeguintesDados() {

        System.out.println("");
    }
}
