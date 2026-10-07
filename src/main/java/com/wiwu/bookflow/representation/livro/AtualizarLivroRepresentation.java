package com.wiwu.bookflow.representation.livro;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

public record AtualizarLivroRepresentation(
        @NotBlank(message = "Titulo é obrigatorio")
        String titulo,

        @NotBlank(message = "ISBN é obrigatorio")
        String isbn,

        Integer anoPublicado,

        Long autorId,

        Long categoriaId
) {
}
