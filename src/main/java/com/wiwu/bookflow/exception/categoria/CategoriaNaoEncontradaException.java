package com.wiwu.bookflow.exception.categoria;

public class CategoriaNaoEncontradaException extends RuntimeException {
    public CategoriaNaoEncontradaException(Long id) {
        super("Categoria com id " + id + " nao encontrada");
    }
}
