package br.al.mcz.agibank.filereader.exceptions;

import java.util.NoSuchElementException;

public class VendaNaoEncontradaException extends NoSuchElementException {

    public VendaNaoEncontradaException() {
        super("Venda não encontrada.");
    }
}
