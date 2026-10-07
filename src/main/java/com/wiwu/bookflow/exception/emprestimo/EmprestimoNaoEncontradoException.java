package com.wiwu.bookflow.exception.emprestimo;

public class EmprestimoNaoEncontradoException extends RuntimeException {
    public EmprestimoNaoEncontradoException(Long id) {
        super("Emprestimo com id " + id + " nao encontrado.");
    }
}
