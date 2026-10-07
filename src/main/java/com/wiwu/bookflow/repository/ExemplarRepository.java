package com.wiwu.bookflow.repository;

import com.wiwu.bookflow.entity.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar,Long> {
}
