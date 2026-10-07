package com.wiwu.bookflow.representation.exemplar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarExemplarRepresentation(
        @NotBlank(message = "Livro é obrigatoria")
        String codigo,

        @NotNull(message = "Livro é obrigatorio")
        Long livroId
) {
}
