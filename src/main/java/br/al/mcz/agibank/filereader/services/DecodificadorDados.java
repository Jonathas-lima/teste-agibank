package br.al.mcz.agibank.filereader.services;

public interface DecodificadorDados <T> {

    T decodificar(String dados);
}
