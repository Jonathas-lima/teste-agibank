package br.al.mcz.agibank.filereader.entities;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Getter
@SuperBuilder
public class Vendedor extends Pessoa implements Entidade, Serializable {

    private BigDecimal salario;

    @Override
    public void printClass() {
        System.out.println(this.getClass().getName());
    }
}
