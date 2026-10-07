package com.wiwu.bookflow.mapper;

import com.wiwu.bookflow.entity.Livro;
import com.wiwu.bookflow.representation.livro.AtualizarLivroRepresentation;
import com.wiwu.bookflow.representation.livro.CriarLivroRepresentation;
import com.wiwu.bookflow.representation.livro.LivroRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "autor", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    Livro toEntity(CriarLivroRepresentation criarLivroRepresentation);

    @Mapping(source = "autor.id", target = "autorId")
    @Mapping(source = "autor.nome", target = "nomeAutor")
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nome", target = "nomeCategoria")
    LivroRepresentation toRepresentation(Livro livro);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "autor", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    void updateEntity(AtualizarLivroRepresentation representation, @MappingTarget Livro livro);
}
