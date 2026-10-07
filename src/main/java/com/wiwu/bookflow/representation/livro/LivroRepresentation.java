package com.wiwu.bookflow.representation.livro;

public record LivroRepresentation(
        Long id,
        String titulo,
        String isbn,
        Integer anoPublicado,
        Long autorId,
        String nomeAutor,
        Long categoriaId,
        String nomeCategoria
) {
}
