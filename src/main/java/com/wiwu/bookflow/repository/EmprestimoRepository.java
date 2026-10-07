package com.wiwu.bookflow.repository;

import com.wiwu.bookflow.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
}
