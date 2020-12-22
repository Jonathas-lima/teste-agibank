package br.al.mcz.agibank.filereader.exceptions;

public class TipoDadoInvalidoException extends IllegalArgumentException{

    public TipoDadoInvalidoException(){
        super("Tipo de dado inválido.");
    }
}
