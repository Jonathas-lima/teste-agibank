package br.al.mcz.agibank.filereader.entities;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public class AnaliseDados implements Serializable {

    private List<Venda> vendas;
    private List<Cliente> clientes;
    private List<Vendedor> vendedores;

    public Integer obterQuantidadeClientes() {
        return clientes.size();
    }

    public Integer obterQuantidadeVendedores() {
        return vendedores.size();
    }

}
