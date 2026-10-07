package com.wiwu.bookflow.entity;

import com.wiwu.bookflow.enums.StatusExemplar;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exemplares")
@Getter
@Setter
@NoArgsConstructor
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @Enumerated(EnumType.STRING)
    private StatusExemplar status;

    @ManyToOne
    @JoinColumn(name = "livros_id")
    private Livro livro;
}
