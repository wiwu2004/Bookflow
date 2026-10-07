package com.wiwu.bookflow.mapper;

import com.wiwu.bookflow.entity.Exemplar;
import com.wiwu.bookflow.representation.exemplar.CriarExemplarRepresentation;
import com.wiwu.bookflow.representation.exemplar.ExemplarRepresentation;
import jakarta.persistence.MapsId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

@Mapper(componentModel = "spring")
public interface ExemplarMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "livro", ignore = true)
    Exemplar toEntity(CriarExemplarRepresentation representation);

    @Mapping(target = "livroId", source = "livro.id")
    @Mapping(target = "tituloLivro", source = "livro.titulo")
    ExemplarRepresentation toRepresentation(Exemplar exemplar);

};

