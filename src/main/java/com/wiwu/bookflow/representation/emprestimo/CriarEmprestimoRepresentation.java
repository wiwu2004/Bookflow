package com.wiwu.bookflow.representation.emprestimo;

import jakarta.validation.constraints.NotNull;

public record CriarEmprestimoRepresentation(
        @NotNull(message = "Usuario é obrigatorio.")
        Long usuarioId,
        @NotNull(message = "Exemplar é obrigatorio.")
        Long exemplarId
) {
}
