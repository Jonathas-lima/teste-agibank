package br.al.mcz.agibank.filereader.entities;


import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class Pessoa implements Entidade, Serializable {

    private String nome;
    private String numeroDocumento;

}
