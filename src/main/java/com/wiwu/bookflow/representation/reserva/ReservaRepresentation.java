package com.wiwu.bookflow.representation.reserva;

import java.time.LocalDateTime;

public record ReservaRepresentation(
        Long id,
        LocalDateTime dataReserva,
        Long usuarioId,
        String nomeUsuario,
        Long exemplarId,
        String codigoExemplar
) {
}
