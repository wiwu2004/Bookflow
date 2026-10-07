package com.wiwu.bookflow.representation.emprestimo;

import java.time.LocalDate;

public record EmprestimoRepresentation(
        Long id,
        LocalDate dataEmprestimo,
        LocalDate dataPrevistaDevolucao,
        LocalDate dataDevolucao,
        Long usuarioId,
        String nomeUsuario,
        Long exemplarId,
        String codigoExemplar
) {
}
