package com.wiwu.bookflow.mapper;

import com.wiwu.bookflow.entity.Usuario;
import com.wiwu.bookflow.representation.usuario.AtualizarUsuarioRepresentation;
import com.wiwu.bookflow.representation.usuario.CriarUsuarioRepresentation;
import com.wiwu.bookflow.representation.usuario.UsuarioRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    Usuario toEntity(CriarUsuarioRepresentation request);

    UsuarioRepresentation toRepresentation(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    void updateEntity(AtualizarUsuarioRepresentation representation, @MappingTarget Usuario usuario);
}

