package br.al.mcz.agibank.filereader.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import static br.al.mcz.agibank.filereader.util.MathUtil.multiplicar;

@Data
@Builder
public class Venda implements Entidade, Serializable {

    private Integer idVenda;
    private List<ItemVenda> listaItens;
    private String nomeVendedor;

    public BigDecimal obterValorVenda(){
        return listaItens.stream()
                .map(itemVenda -> multiplicar(itemVenda.getQuantidade(),itemVenda.getPreco()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
