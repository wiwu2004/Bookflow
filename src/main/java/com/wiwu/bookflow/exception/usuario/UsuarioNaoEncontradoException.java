package com.wiwu.bookflow.exception.usuario;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuario com id " + id + " nao encontrado");
    }
}
