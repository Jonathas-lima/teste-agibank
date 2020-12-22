package br.al.mcz.agibank.filereader.batch;

import br.al.mcz.agibank.filereader.entities.*;
import br.al.mcz.agibank.filereader.entities.types.TipoDado;
import br.al.mcz.agibank.filereader.exceptions.TipoDadoInvalidoException;
import br.al.mcz.agibank.filereader.services.DecodificadorDados;
import br.al.mcz.agibank.filereader.services.impl.DecodificadorDadosCliente;
import br.al.mcz.agibank.filereader.services.impl.DecodificadorDadosVenda;
import br.al.mcz.agibank.filereader.services.impl.DecodificadorDadosVendedor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;
import java.util.stream.Collectors;


import static br.al.mcz.agibank.filereader.entities.types.TipoDado.obterPorCodigo;

public class LinesProcessor implements Tasklet, StepExecutionListener {

    private List<String> lines;
    private List<Entidade> entidades;
    private AnaliseDados analiseDados;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.lines = (List<String>) executionContext.get("lines");

    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        entidades = lines.stream()
                .map(this::process)
                .collect(Collectors.toList());

        this.analiseDados = montarAnaliseDados();

        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("analiseDados", this.analiseDados);
        return ExitStatus.COMPLETED;
    }

    private AnaliseDados montarAnaliseDados() {
        return AnaliseDados.builder()
                .clientes((List<Cliente>)obterEntidadePorTipo(Cliente.class))
                .vendedores((List<Vendedor>) obterEntidadePorTipo(Vendedor.class))
                .vendas((List<Venda>) obterEntidadePorTipo(Venda.class))
                .build();
    }

    private List<?> obterEntidadePorTipo(Class<?> typeClass) {
        return this.entidades.stream()
                .filter(entidade -> entidade.getClass().equals(typeClass))
                .collect(Collectors.toList());
    }


    private Entidade process(String linha) {
        DecodificadorDados<?> decodificadorDados = obterDecodificador(obterTipoDado(linha));
        return (Entidade) decodificadorDados.decodificar(linha);
    }

    private TipoDado obterTipoDado(String dados) {
        return obterPorCodigo(dados.substring(0, 3));
    }

    private DecodificadorDados<?> obterDecodificador(TipoDado tipo) {
        switch (tipo) {
            case VENDEDOR:
                return new DecodificadorDadosVendedor();
            case VENDA:
                return new DecodificadorDadosVenda();
            case CLIENTE:
                return new DecodificadorDadosCliente();
            default:
                throw new TipoDadoInvalidoException();
        }
    }
}