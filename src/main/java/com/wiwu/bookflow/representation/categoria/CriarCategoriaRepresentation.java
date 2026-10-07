package com.wiwu.bookflow.representation.categoria;

import jakarta.validation.constraints.NotBlank;

public record CriarCategoriaRepresentation(
        @NotBlank(message = "nome é obrigatorio")
        String nome,
        String descricao
) {
}
