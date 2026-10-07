package com.wiwu.bookflow.exception.autor;

public class AutorNaoEncontradoException extends RuntimeException {
    public AutorNaoEncontradoException(Long id) {

        super("Autor com id " + id + " nao encontrado.");
    }
}
