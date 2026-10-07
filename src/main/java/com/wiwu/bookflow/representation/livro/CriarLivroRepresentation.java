package com.wiwu.bookflow.representation.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarLivroRepresentation(
        @NotBlank(message = "Titulo é obrigatorio")
        String titulo,

        @NotBlank(message = "ISBN é obrigatorio")
        String isbn,

        Integer anoPublicado,

        @NotNull(message = "Autor é obrigatorio")
        Long autorId,

        @NotNull(message = "Categoria é obrigatoria")
        Long categoriaId
) {
}
