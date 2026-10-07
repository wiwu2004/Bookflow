package com.wiwu.bookflow.representation.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriarUsuarioRepresentation(
        @NotBlank(message = "Nome é obrigatio")
        String nome,

        @NotBlank(message = "emial é obrigatorio")
        @Email
        String email
) {
}
