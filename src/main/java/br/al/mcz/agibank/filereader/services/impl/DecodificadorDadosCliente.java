package br.al.mcz.agibank.filereader.services.impl;

import br.al.mcz.agibank.filereader.entities.Cliente;
import br.al.mcz.agibank.filereader.services.DecodificadorDados;
import org.springframework.stereotype.Component;

import static br.al.mcz.agibank.filereader.shared.Constantes.Separadores.SEPARDOR_DADOS;

@Component
public class DecodificadorDadosCliente implements DecodificadorDados<Cliente> {

    @Override
    public Cliente decodificar(String dados) {

        String[] dadosCliente = dados.split(SEPARDOR_DADOS);

        return Cliente.builder()
                .numeroDocumento(dadosCliente[1])
                .nome(dadosCliente[2])
                .areaNegocio(dadosCliente[3])
                .build();
    }
}
