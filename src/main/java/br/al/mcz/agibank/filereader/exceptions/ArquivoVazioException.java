package br.al.mcz.agibank.filereader.exceptions;

import java.util.NoSuchElementException;

public class ArquivoVazioException extends NoSuchElementException {

    public ArquivoVazioException() {
        super("Arquivo vazio ou não pode ser processado.");
    }
}
