package com.wiwu.bookflow.mapper;

import com.wiwu.bookflow.entity.HistoricoEvento;
import com.wiwu.bookflow.representation.historico.HistoricoEventoRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoricoEventoMapper {

    HistoricoEventoRepresentation toRepresentation(HistoricoEvento historicoEvento);

}
