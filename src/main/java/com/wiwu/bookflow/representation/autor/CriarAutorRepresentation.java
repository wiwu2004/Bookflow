package com.wiwu.bookflow.representation.autor;

import jakarta.validation.constraints.NotBlank;

public record CriarAutorRepresentation(
        @NotBlank(message = "nome é obrigatorio")
        String nome,
        String biografia
) {
}
