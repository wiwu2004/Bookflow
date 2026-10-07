package com.wiwu.bookflow.mapper;

import com.wiwu.bookflow.entity.Reserva;
import com.wiwu.bookflow.representation.reserva.CriarReservaRepresentation;
import com.wiwu.bookflow.representation.reserva.ReservaRepresentation;
import com.wiwu.bookflow.representation.usuario.CriarUsuarioRepresentation;
import jakarta.persistence.MapsId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataReserva", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "exemplar", ignore = true)
    Reserva toEntity(CriarReservaRepresentation representation);

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "nomeUsuario", source = "usuario.nome")
    @Mapping(target = "codigoExemplar", source = "exemplar.codigo")
    @Mapping(target = "exemplarId", source = "exemplar.id")
    ReservaRepresentation toRepresentation(Reserva reserva);
}

