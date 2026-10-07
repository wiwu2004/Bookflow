package com.wiwu.bookflow.service.kafka;

import com.wiwu.bookflow.entity.HistoricoEvento;
import com.wiwu.bookflow.event.EmprestimoCriadoEvent;
import com.wiwu.bookflow.repository.HistoricoEventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EmprestimoCriadoConsumer {

    private final HistoricoEventoRepository historicoEventoRepository;

    @KafkaListener(topics = "emprestimo-criado", groupId = "bookflow-group")
    public void consumir(EmprestimoCriadoEvent event){
        HistoricoEvento historico = new HistoricoEvento();
        historico.setTipoEvento("EMPRESTIMO_CRIADO");
        historico.setDescricao("Emprestimo %d criado para usuario %d e exemplar %d".formatted(event.emprestimoId(),event.usuarioId(),event.exemplarId()));
        historico.setDataHora(LocalDateTime.now());
        historicoEventoRepository.save(historico);
    }
}
