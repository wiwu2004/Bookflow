package com.wiwu.bookflow.service.kafka;

import com.wiwu.bookflow.event.EmprestimoCriadoEvent;
import com.wiwu.bookflow.event.EmprestimoDevolvidoEvent;
import com.wiwu.bookflow.event.ReservaCriadaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;


    public void publicarEmprestimoCriado(EmprestimoCriadoEvent event){
        System.out.println("PUBLICANDO: " + event);
        kafkaTemplate.send("emprestimo-criado",event);
    }

    public void publicarEmprestimoDevolvido(EmprestimoDevolvidoEvent event){
        kafkaTemplate.send("emprestimo-devolvido",event);
    }

    public void publicarReservaCriada(
            ReservaCriadaEvent event) {

        kafkaTemplate.send(
                "reserva-criada",
                event
        );
    }
}
