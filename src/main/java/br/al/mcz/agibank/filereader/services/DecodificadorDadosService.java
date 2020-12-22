package br.al.mcz.agibank.filereader.services;

public interface DecodificadorDadosService<T> {

    T decodificar(String dados);
}
