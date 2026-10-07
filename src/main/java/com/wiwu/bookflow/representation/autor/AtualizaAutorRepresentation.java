package com.wiwu.bookflow.representation.autor;

import jakarta.validation.constraints.NotBlank;

public record AtualizaAutorRepresentation(
        @NotBlank(message = "nome é obrigatorio")
        String nome,
        String biografia
) {
}
