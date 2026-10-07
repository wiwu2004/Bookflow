package com.wiwu.bookflow.service.kafka;

import com.wiwu.bookflow.entity.HistoricoEvento;
import com.wiwu.bookflow.repository.HistoricoEventoRepository;
import com.wiwu.bookflow.event.EmprestimoDevolvidoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EmprestimoDevolvidoConsumer {

    private final HistoricoEventoRepository historicoEventoRepository;

    @KafkaListener(topics = "emprestimo-devolvido",groupId = "bookflow-group")
    public void consumir(EmprestimoDevolvidoEvent event){

        HistoricoEvento historico = new HistoricoEvento();

        historico.setTipoEvento("EMPRESTIMO_DEVOLVIDO");

        historico.setDescricao("Emprestimo %d devolvido pelo usuário %d do exemplar %d"
                .formatted(
                        event.emprestimoId(),
                        event.usuarioId(),
                        event.exemplarId()
                ));

        historico.setDataHora(LocalDateTime.now());

        historicoEventoRepository.save(historico);

    }
}
