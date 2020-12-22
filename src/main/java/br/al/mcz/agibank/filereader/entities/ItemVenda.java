package br.al.mcz.agibank.filereader.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class ItemVenda implements Entidade, Serializable {

    private Integer itemId;
    private BigDecimal preco;
    private Integer quantidade = 1;


    @Override
    public void printClass() {
        System.out.println(this.getClass().getName());
    }
}
