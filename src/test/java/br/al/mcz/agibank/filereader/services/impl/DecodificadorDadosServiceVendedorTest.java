package br.al.mcz.agibank.filereader.services.impl;

import br.al.mcz.agibank.filereader.entities.Vendedor;
import br.al.mcz.agibank.filereader.services.DecodificadorDadosService;
import org.junit.Before;
import org.junit.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class DecodificadorDadosServiceVendedorTest {

    DecodificadorDadosService<?> decodificadorDadosService;

    @Before
    public void init() {
        decodificadorDadosService = new DecodificadorDadosServiceVendedor();
    }

    @Test
    public void decodificarComCodigoValido() {

        String linhaDadosVendedor = "001ç3245678865434çPauloç40000.99";
        Vendedor vendedor = (Vendedor) decodificadorDadosService.decodificar(linhaDadosVendedor);

        assertNotNull(vendedor);
        assertEquals("Paulo", vendedor.getNome());
        assertEquals("3245678865434", vendedor.getNumeroDocumento());
        assertEquals(new BigDecimal("40000.99"), vendedor.getSalario());
    }

    @Test
    public void decodificarComCodigoClienteInvalido(){
        String linhaDadosCliente = "005ç2345675434544345çJose da SilvaçRural";
        assertThrows(IllegalArgumentException.class, ()-> decodificadorDadosService.decodificar(linhaDadosCliente));
    }
}