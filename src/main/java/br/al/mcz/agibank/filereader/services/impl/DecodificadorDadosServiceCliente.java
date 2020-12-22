package br.al.mcz.agibank.filereader.services.impl;

import br.al.mcz.agibank.filereader.entities.Cliente;
import br.al.mcz.agibank.filereader.services.DecodificadorDadosService;
import org.springframework.stereotype.Component;

import static br.al.mcz.agibank.filereader.entities.types.TipoDado.*;
import static br.al.mcz.agibank.filereader.shared.Constantes.Separadores.SEPARDOR_DADOS;

@Component
public class DecodificadorDadosServiceCliente implements DecodificadorDadosService<Cliente> {

    @Override
    public Cliente decodificar(String dados) {
        validarEntrada(dados);

        String[] dadosCliente = dados.split(SEPARDOR_DADOS);

        return Cliente.builder()
                .numeroDocumento(dadosCliente[1])
                .nome(dadosCliente[2])
                .areaNegocio(dadosCliente[3])
                .build();
    }

    private void validarEntrada(String dados) {
        validarSeTipoCliente(dados);
    }

    private void validarSeTipoCliente(String dados) {
        if (!CLIENTE.equals(obterTipoDadoPorLinha(dados))) {
            throw new IllegalArgumentException("Tipo de dado incompativel");
        }
    }
}
