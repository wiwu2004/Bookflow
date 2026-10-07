package com.wiwu.bookflow.exception.livro;

public class LivroNaoEncontradoException extends RuntimeException {
    public LivroNaoEncontradoException(Long id) {
        super("Livro com id " + id + " não encontrado");
    }

}
