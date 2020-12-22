package br.al.mcz.agibank.filereader.entities;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class Cliente extends Pessoa implements Entidade, Serializable {

    private String areaNegocio;
}
