package br.al.mcz.agibank.filereader.services.impl;

import br.al.mcz.agibank.filereader.entities.Vendedor;
import br.al.mcz.agibank.filereader.services.DecodificadorDados;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.al.mcz.agibank.filereader.shared.Constantes.Separadores.SEPARDOR_DADOS;

public class DecodificadorDadosVendedor implements DecodificadorDados<Vendedor> {

    @Override
    public Vendedor decodificar(String dados) {

        String[] dadosVendedor = dados.split(SEPARDOR_DADOS);

        return Vendedor.builder()
                .numeroDocumento(dadosVendedor[1])
                .nome(dadosVendedor[2])
                .salario(new BigDecimal(dadosVendedor[3]))
                .build();
    }
}
