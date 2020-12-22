package br.al.mcz.agibank.filereader.entities;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@SuperBuilder
public class Vendedor extends Pessoa implements Entidade, Serializable {

    private BigDecimal salario;
}
