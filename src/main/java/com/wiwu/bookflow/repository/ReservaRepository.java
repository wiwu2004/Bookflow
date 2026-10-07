package com.wiwu.bookflow.repository;

import com.wiwu.bookflow.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva,Long> {
    boolean existsByUsuarioIdAndExemplarId(
            Long usuarioId, Long exemplarId
    );


}
