package com.wiwu.bookflow.service.kafka;

import com.wiwu.bookflow.entity.HistoricoEvento;
import com.wiwu.bookflow.event.ReservaCriadaEvent;
import com.wiwu.bookflow.repository.HistoricoEventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ReservaCriadaConsumer {

    private final HistoricoEventoRepository historicoEventoRepository;

    @KafkaListener(
            topics = "reserva-criada",
            groupId = "bookflow-group"
    )
    public void consumir(
            ReservaCriadaEvent event
    ) {

        HistoricoEvento historico = new HistoricoEvento();

        historico.setTipoEvento(
                "RESERVA_CRIADA"
        );

        historico.setDescricao(
                "Reserva %d criada pelo usuário %d para o exemplar %d"
                        .formatted(
                                event.reservaId(),
                                event.usuarioId(),
                                event.exemplarId()
                        )
        );

        historico.setDataHora(
                LocalDateTime.now()
        );

        historicoEventoRepository.save(
                historico
        );
    }
}