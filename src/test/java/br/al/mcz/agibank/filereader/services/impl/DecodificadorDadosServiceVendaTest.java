package br.al.mcz.agibank.filereader.services.impl;

import br.al.mcz.agibank.filereader.entities.Venda;
import br.al.mcz.agibank.filereader.services.DecodificadorDadosService;
import org.junit.Before;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class DecodificadorDadosServiceVendaTest {

    DecodificadorDadosService<?> decodificadorDadosService;

    @Before
    public void init() {
        decodificadorDadosService = new DecodificadorDadosServiceVenda();
    }

    @Test
    public void decodificarComCodigoValido() {
        String linhaDadosVenda = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        Venda venda = (Venda) decodificadorDadosService.decodificar(linhaDadosVenda);

        assertNotNull(venda);
        assertFalse(venda.getListaItens().isEmpty());
        assertEquals("Pedro", venda.getNomeVendedor());
        assertEquals(10, venda.getIdVenda());
    }

    @Test
    public void decodificarComCodigoClienteInvalido(){
        String linhaDadosCliente = "005ç2345675434544345çJose da SilvaçRural";
        assertThrows(IllegalArgumentException.class, ()-> decodificadorDadosService.decodificar(linhaDadosCliente));
    }
}