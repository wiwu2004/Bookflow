package com.wiwu.bookflow.event;

public record ReservaCriadaEvent(
        Long reservaId,
        Long usuarioId,
        Long exemplarId
) {

}
