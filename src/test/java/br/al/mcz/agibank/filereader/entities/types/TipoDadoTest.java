package br.al.mcz.agibank.filereader.entities.types;

import br.al.mcz.agibank.filereader.exceptions.TipoDadoInvalidoException;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TipoDadoTest {


    @Test
    public void obterPorCodigo() {
        TipoDado tipoEperado = TipoDado.CLIENTE;
        String codCliente = "002";

        TipoDado tipoDadoObtido = TipoDado.obterPorCodigo(codCliente);

        assertNotNull(tipoDadoObtido);
        assertEquals(tipoEperado, tipoDadoObtido);
    }

    @Test
    public void obterTipoDadoPorLinha() {
        TipoDado tipoDadoEsperado = TipoDado.VENDEDOR;
        String linhaDados = "001ç1234567891234çPedroç50000";

        TipoDado tipoDadoObtido = TipoDado.obterTipoDadoPorLinha(linhaDados);

        assertNotNull(tipoDadoObtido);
        assertEquals(tipoDadoEsperado, tipoDadoObtido);
    }

    @Test()
    public void obterPorCodigoInvalido() {
        String codigoInvalido = "005";
        assertThrows(TipoDadoInvalidoException.class, ()-> TipoDado.obterPorCodigo(codigoInvalido));
    }
}