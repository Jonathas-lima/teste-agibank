package br.al.mcz.agibank.filereader.exceptions;

import java.nio.file.NoSuchFileException;

public class ArquivoNaoEncontradoException extends NoSuchFileException {

    public ArquivoNaoEncontradoException() {
        super("Arquivo não encontrado no caminho especificado.");
    }
}
