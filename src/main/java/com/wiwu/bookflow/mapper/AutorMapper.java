package com.wiwu.bookflow.mapper;

import com.wiwu.bookflow.entity.Autor;
import com.wiwu.bookflow.representation.autor.AtualizaAutorRepresentation;
import com.wiwu.bookflow.representation.autor.AutorRepresentation;
import com.wiwu.bookflow.representation.autor.CriarAutorRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(target = "id", ignore = true)
    Autor toEntity(CriarAutorRepresentation representation);

    AutorRepresentation toRepresentation(Autor autor);

    @Mapping(target = "id", ignore = true)
    void updateEntity(
            AtualizaAutorRepresentation representation, @MappingTarget Autor autor
    );
}
