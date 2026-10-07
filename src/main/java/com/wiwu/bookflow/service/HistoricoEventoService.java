package com.wiwu.bookflow.service;

import com.wiwu.bookflow.entity.HistoricoEvento;
import com.wiwu.bookflow.mapper.HistoricoEventoMapper;
import com.wiwu.bookflow.repository.HistoricoEventoRepository;
import com.wiwu.bookflow.representation.historico.HistoricoEventoRepresentation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoricoEventoService {

    private final HistoricoEventoRepository historicoEventoRepository;
    private final HistoricoEventoMapper historicoEventoMapper;

    public List<HistoricoEventoRepresentation> listar(
            String tipoEvento) {

        if (tipoEvento == null || tipoEvento.isBlank()) {
            return historicoEventoRepository.findAll()
                    .stream()
                    .map(historicoEventoMapper::toRepresentation)
                    .toList();
        }

        return historicoEventoRepository
                .findByTipoEvento(tipoEvento)
                .stream()
                .map(historicoEventoMapper::toRepresentation)
                .toList();
    }

}
