package br.al.mcz.agibank.filereader.exceptions;

import java.util.NoSuchElementException;

public class VendedorNaoEncontradoException extends NoSuchElementException {

    public VendedorNaoEncontradoException() {
        super("Vendedor não encontrado.");
    }
}
