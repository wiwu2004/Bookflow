package com.wiwu.bookflow.exception.exemplar;

public class ExemplarNaoEncontradoException extends RuntimeException {
    public ExemplarNaoEncontradoException(Long id) {
        super("exemplar com id " + id + " nao encontrado");
    }
}
