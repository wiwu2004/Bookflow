package com.wiwu.bookflow.repository;

import com.wiwu.bookflow.entity.HistoricoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoEventoRepository extends JpaRepository<HistoricoEvento,Long> {

    List<HistoricoEvento> findByTipoEvento(String tipoEvento);
}
