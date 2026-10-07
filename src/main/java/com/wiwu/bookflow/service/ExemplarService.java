package com.wiwu.bookflow.service;

import com.wiwu.bookflow.entity.Exemplar;
import com.wiwu.bookflow.entity.Livro;
import com.wiwu.bookflow.enums.StatusExemplar;
import com.wiwu.bookflow.exception.exemplar.ExemplarNaoEncontradoException;
import com.wiwu.bookflow.exception.livro.LivroNaoEncontradoException;
import com.wiwu.bookflow.mapper.ExemplarMapper;
import com.wiwu.bookflow.repository.ExemplarRepository;
import com.wiwu.bookflow.repository.LivroRepository;
import com.wiwu.bookflow.representation.exemplar.CriarExemplarRepresentation;
import com.wiwu.bookflow.representation.exemplar.ExemplarRepresentation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExemplarService {

    private final ExemplarRepository exemplarRepository;
    private  final LivroRepository livroRepository;
    private final ExemplarMapper exemplarMapper;

    public ExemplarRepresentation criar(CriarExemplarRepresentation representation){
        Livro livro = livroRepository
                .findById(representation.livroId())
                .orElseThrow(() -> new LivroNaoEncontradoException(representation.livroId()));

        Exemplar exemplar = exemplarMapper.toEntity(representation);

        exemplar.setLivro(livro);
        exemplar.setStatus(StatusExemplar.DISPONIVEL);

        Exemplar exemplarSalvo = exemplarRepository.save(exemplar);

        return exemplarMapper.toRepresentation(exemplarSalvo);

    }

    public List<ExemplarRepresentation> listar(){
        return exemplarRepository.findAll()
                .stream()
                .map(exemplarMapper::toRepresentation)
                .toList();
    }

    public ExemplarRepresentation buscarPorId(Long id){
        Exemplar exemplar = exemplarRepository.findById(id)
                .orElseThrow(() -> new ExemplarNaoEncontradoException(id));

        return exemplarMapper.toRepresentation(exemplar);
    }

    public void deletar(Long id){
        if (!exemplarRepository.existsById(id)){
            throw  new ExemplarNaoEncontradoException(id);
        }
        exemplarRepository.deleteById(id);
    }

}
