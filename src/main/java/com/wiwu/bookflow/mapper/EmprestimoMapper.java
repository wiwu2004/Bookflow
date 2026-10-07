package com.wiwu.bookflow.mapper;

import com.wiwu.bookflow.entity.Emprestimo;
import com.wiwu.bookflow.representation.emprestimo.CriarEmprestimoRepresentation;
import com.wiwu.bookflow.representation.emprestimo.EmprestimoRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataEmprestimo", ignore = true)
    @Mapping(target = "dataPrevistaDevolucao", ignore = true)
    @Mapping(target = "dataDevolucao", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "exemplar", ignore = true)
    Emprestimo toEntity (CriarEmprestimoRepresentation request);

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "nomeUsuario", source = "usuario.nome")
    @Mapping(target = "exemplarId", source = "exemplar.id")
    @Mapping(target = "codigoExemplar", source = "exemplar.codigo")
    EmprestimoRepresentation toRepresentation(Emprestimo emprestimo);
}
