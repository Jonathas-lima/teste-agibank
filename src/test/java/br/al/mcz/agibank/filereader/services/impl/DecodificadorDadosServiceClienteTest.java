package br.al.mcz.agibank.filereader.services.impl;

import br.al.mcz.agibank.filereader.entities.Cliente;
import br.al.mcz.agibank.filereader.services.DecodificadorDadosService;
import org.junit.Before;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class DecodificadorDadosServiceClienteTest {

    DecodificadorDadosService<?> decodificadorDadosService;

    @Before
    public void init() {
        decodificadorDadosService = new DecodificadorDadosServiceCliente();
    }

    @Test
    public void decodificarComCodigoValido() {

        String linhaDadosCliente = "002ç2345675434544345çJose da SilvaçRural";
        Cliente cliente = (Cliente) decodificadorDadosService.decodificar(linhaDadosCliente);

        assertNotNull(cliente);
        assertEquals("Jose da Silva", cliente.getNome());
        assertEquals("2345675434544345", cliente.getNumeroDocumento());
        assertEquals("Rural", cliente.getAreaNegocio());
    }

    @Test
    public void decodificarComCodigoClienteInvalido(){
        String linhaDadosCliente = "005ç2345675434544345çJose da SilvaçRural";
        assertThrows(IllegalArgumentException.class, ()-> decodificadorDadosService.decodificar(linhaDadosCliente));
    }
}