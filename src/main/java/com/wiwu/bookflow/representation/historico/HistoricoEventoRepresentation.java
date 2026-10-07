package com.wiwu.bookflow.representation.historico;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record HistoricoEventoRepresentation(
        Long id,
        String tipoEvento,
        String descricao,
        LocalDateTime dataHora
) {
}
