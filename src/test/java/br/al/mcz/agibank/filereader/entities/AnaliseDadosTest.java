package br.al.mcz.agibank.filereader.entities;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnaliseDadosTest {

    private AnaliseDados analiseDados;

    @Before
    public void init() {
        preencherValores();
    }

    @Test
    public void obterQuantidadeClientes() {
        Integer quantidadeEsperada = 1;
        Integer quantidadeObitida = this.analiseDados.obterQuantidadeClientes();

        assertNotNull(quantidadeObitida);
        assertEquals(quantidadeEsperada, quantidadeObitida);
    }

    @Test
    public void obterQuantidadeVendedores() {
        Integer quantidadeEsperada = 2;
        Integer quantidadeObitida = this.analiseDados.obterQuantidadeVendedores();

        assertNotNull(quantidadeObitida);
        assertEquals(quantidadeEsperada, quantidadeObitida);
    }

    @Test
    public void obterIdVendaMaisCara() {
        Integer idEsperada = 1;
        Integer idObitido = this.analiseDados.obterIdVendaMaisCara();

        assertNotNull(idObitido);
        assertEquals(idEsperada, idEsperada);
    }

    @Test
    public void obterPiorVendedor() {
        String nomeEsperado = "vendedor2";
        String documentoEsperado = "2222";

        Vendedor vendedorObitido = this.analiseDados.obterPiorVendedor();

        assertNotNull(vendedorObitido);
        assertEquals(nomeEsperado, vendedorObitido.getNome());
        assertEquals(documentoEsperado, vendedorObitido.getNumeroDocumento());
    }

    @Test
    public void obterResumoAnalise() {
        String resumoAnalise = this.analiseDados.obterResumoAnalise();
        assertNotNull(resumoAnalise);
    }

    public void preencherValores() {
        Cliente cliente = Cliente.builder().nome("jose").numeroDocumento("00000").build();
        Vendedor vendedor1 = Vendedor.builder().nome("vendedor1").numeroDocumento("1111").build();
        Vendedor vendedor2 = Vendedor.builder().nome("vendedor2").numeroDocumento("2222").build();

        ItemVenda itemVenda1 = ItemVenda.builder().itemId(1).quantidade(10).preco(BigDecimal.TEN).build();
        ItemVenda itemVenda2 = ItemVenda.builder().itemId(2).quantidade(1).preco(BigDecimal.ONE).build();

        Venda venda1 = Venda.builder().nomeVendedor("vendedor1").idVenda(1).listaItens(singletonList(itemVenda1)).build();
        Venda venda2 = Venda.builder().nomeVendedor("vendedor2").idVenda(2).listaItens(singletonList(itemVenda2)).build();


        this.analiseDados = AnaliseDados.builder()
                                        .clientes(singletonList(cliente))
                                        .vendas(Arrays.asList(venda1, venda2))
                                        .vendedores(Arrays.asList(vendedor1,vendedor2))
                                        .build();
    }
}