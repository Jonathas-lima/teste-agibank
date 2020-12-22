package br.al.mcz.agibank.filereader.entities;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@Getter
@SuperBuilder
public class Cliente extends Pessoa implements Entidade, Serializable {

    private String areaNegocio;

    @Override
    public void printClass() {
        System.out.println(this.getClass().getName());
    }
}
