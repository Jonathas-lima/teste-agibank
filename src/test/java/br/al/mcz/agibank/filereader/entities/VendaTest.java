package br.al.mcz.agibank.filereader.entities;


import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VendaTest {

    @Test
    public void obterValorVenda() {

        BigDecimal valorVendaEsperado = new BigDecimal(101);

        ItemVenda itemVenda1 = ItemVenda.builder().itemId(1).quantidade(10).preco(BigDecimal.TEN).build();
        ItemVenda itemVenda2 = ItemVenda.builder().itemId(2).quantidade(1).preco(BigDecimal.ONE).build();

        Venda venda = Venda.builder().nomeVendedor("vendedor1").idVenda(1).listaItens(Arrays.asList(itemVenda1, itemVenda2)).build();

        BigDecimal valorVendaObtido = venda.obterValorVenda();

        assertNotNull(valorVendaObtido);
        assertEquals(valorVendaEsperado, valorVendaObtido);
    }
}