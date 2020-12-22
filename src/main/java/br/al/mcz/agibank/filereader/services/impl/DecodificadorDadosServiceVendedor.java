package br.al.mcz.agibank.filereader.services.impl;

import br.al.mcz.agibank.filereader.entities.Vendedor;
import br.al.mcz.agibank.filereader.services.DecodificadorDadosService;

import java.math.BigDecimal;

import static br.al.mcz.agibank.filereader.entities.types.TipoDado.VENDEDOR;
import static br.al.mcz.agibank.filereader.entities.types.TipoDado.obterTipoDadoPorLinha;
import static br.al.mcz.agibank.filereader.shared.Constantes.Separadores.SEPARDOR_DADOS;

public class DecodificadorDadosServiceVendedor implements DecodificadorDadosService<Vendedor> {

    @Override
    public Vendedor decodificar(String dados) {
        validarEntrada(dados);
        String[] dadosVendedor = dados.split(SEPARDOR_DADOS);

        return Vendedor.builder()
                .numeroDocumento(dadosVendedor[1])
                .nome(dadosVendedor[2])
                .salario(new BigDecimal(dadosVendedor[3]))
                .build();
    }

    private void validarEntrada(String dados) {
        validarSeTipoVendedor(dados);
    }

    private void validarSeTipoVendedor(String dados) {
        if (!VENDEDOR.equals(obterTipoDadoPorLinha(dados))) {
            throw new IllegalArgumentException("Tipo de dado incompativel");
        }
    }
}
