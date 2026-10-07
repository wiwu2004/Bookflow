package com.wiwu.bookflow.exception.reserva;

public class ReservaNaoEncontradaException extends RuntimeException {
    public ReservaNaoEncontradaException(Long id) {
        super("Reserva com id " + id + " nao encontrada");
    }
}
