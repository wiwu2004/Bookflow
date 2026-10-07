package com.wiwu.bookflow.representation.exemplar;

import com.wiwu.bookflow.enums.StatusExemplar;

public record ExemplarRepresentation(
        Long id,
        String codigo,
        StatusExemplar status,
        Long livroId,
        String tituloLivro
) {
}
