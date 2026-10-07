package com.wiwu.bookflow.mapper;

import com.wiwu.bookflow.entity.Categoria;
import com.wiwu.bookflow.representation.categoria.AtualizarCategoriaRepresentation;
import com.wiwu.bookflow.representation.categoria.CategoriaRepresentation;
import com.wiwu.bookflow.representation.categoria.CriarCategoriaRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "id", ignore = true)
    Categoria toEntity(CriarCategoriaRepresentation representation);

    CategoriaRepresentation toRepresentation(Categoria categoria);

    @Mapping(target = "id", ignore = true)
    void updateEntity(
            AtualizarCategoriaRepresentation representation,
            @MappingTarget Categoria categoria
    );
}
