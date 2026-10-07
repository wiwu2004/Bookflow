package com.wiwu.bookflow.event;

public record EmprestimoCriadoEvent(
        Long emprestimoId,
        Long usuarioId,
        Long exemplarId
) {
}
