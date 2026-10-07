package com.wiwu.bookflow.service;

import com.wiwu.bookflow.entity.Emprestimo;
import com.wiwu.bookflow.entity.Exemplar;
import com.wiwu.bookflow.entity.Usuario;
import com.wiwu.bookflow.enums.StatusExemplar;
import com.wiwu.bookflow.event.EmprestimoCriadoEvent;
import com.wiwu.bookflow.exception.emprestimo.EmprestimoNaoEncontradoException;
import com.wiwu.bookflow.exception.exemplar.ExemplarNaoEncontradoException;
import com.wiwu.bookflow.exception.usuario.UsuarioNaoEncontradoException;
import com.wiwu.bookflow.mapper.EmprestimoMapper;
import com.wiwu.bookflow.repository.EmprestimoRepository;
import com.wiwu.bookflow.repository.ExemplarRepository;
import com.wiwu.bookflow.repository.UsuarioRepository;
import com.wiwu.bookflow.representation.emprestimo.CriarEmprestimoRepresentation;
import com.wiwu.bookflow.event.EmprestimoDevolvidoEvent;
import com.wiwu.bookflow.representation.emprestimo.EmprestimoRepresentation;
import com.wiwu.bookflow.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ExemplarRepository exemplarRepository;
    private final EmprestimoMapper emprestimoMapper;
    private final KafkaProducer kafkaProducer;

    public EmprestimoRepresentation criar(
            CriarEmprestimoRepresentation representation) {

        Usuario usuario = usuarioRepository
                .findById(representation.usuarioId())
                .orElseThrow(
                        () -> new UsuarioNaoEncontradoException(
                                representation.usuarioId()
                        )
                );

        Exemplar exemplar = exemplarRepository
                .findById(representation.exemplarId())
                .orElseThrow(
                        () -> new ExemplarNaoEncontradoException(
                                representation.exemplarId()
                        )
                );

        if (exemplar.getStatus() != StatusExemplar.DISPONIVEL) {
            throw new IllegalStateException(
                    "Exemplar não está disponível para empréstimo."
            );
        }

        Emprestimo emprestimo =
                emprestimoMapper.toEntity(representation);

        emprestimo.setUsuario(usuario);
        emprestimo.setExemplar(exemplar);

        emprestimo.setDataEmprestimo(
                LocalDate.now()
        );

        emprestimo.setDataPrevistaDevolucao(
                LocalDate.now().plusDays(7)
        );

        exemplar.setStatus(
                StatusExemplar.EMPRESTADO
        );

        exemplarRepository.save(exemplar);

        Emprestimo emprestimoSalvo =
                emprestimoRepository.save(emprestimo);

        kafkaProducer.publicarEmprestimoCriado(
                new EmprestimoCriadoEvent(
                        emprestimoSalvo.getId(),
                        usuario.getId(),
                        exemplar.getId()
                )
        );

        return emprestimoMapper.toRepresentation(
                emprestimoSalvo
        );
    }

    public List<EmprestimoRepresentation> listar() {
        return emprestimoRepository.findAll()
                .stream()
                .map(emprestimoMapper::toRepresentation).toList();
    }

    public EmprestimoRepresentation buscarPorId(Long id){
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException(id));
        return emprestimoMapper.toRepresentation(emprestimo);
    }

    public void deletar(Long id){
        if (!emprestimoRepository.existsById(id)){
            throw new ExemplarNaoEncontradoException(id);
        }
        emprestimoRepository.deleteById(id);
    }

    public EmprestimoRepresentation devolver(Long id){

        Emprestimo emprestimo = emprestimoRepository
                .findById(id)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException(id));

        if (emprestimo.getDataDevolucao() != null) {
            throw new IllegalStateException("Este empréstimo já foi devolvido");
        }

        emprestimo.setDataDevolucao(LocalDate.now());

        Exemplar exemplar = emprestimo.getExemplar();

        exemplar.setStatus(StatusExemplar.DISPONIVEL);

        exemplarRepository.save(exemplar);

        Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimo);

        kafkaProducer.publicarEmprestimoDevolvido(
                new EmprestimoDevolvidoEvent(
                        emprestimoAtualizado.getId(),
                        emprestimoAtualizado.getUsuario().getId(),
                        emprestimoAtualizado.getExemplar().getId()
                )
        );

        return emprestimoMapper.toRepresentation(emprestimoAtualizado);
    }


}
