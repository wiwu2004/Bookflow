package com.wiwu.bookflow.event;

public record EmprestimoDevolvidoEvent(
        Long emprestimoId,
        Long usuarioId,
        Long exemplarId
) {
}
