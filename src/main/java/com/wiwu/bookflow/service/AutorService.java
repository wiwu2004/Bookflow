package com.wiwu.bookflow.service;

import com.wiwu.bookflow.entity.Autor;
import com.wiwu.bookflow.exception.autor.AutorNaoEncontradoException;
import com.wiwu.bookflow.mapper.AutorMapper;
import com.wiwu.bookflow.repository.AutorRepository;
import com.wiwu.bookflow.representation.autor.AtualizaAutorRepresentation;
import com.wiwu.bookflow.representation.autor.AutorRepresentation;
import com.wiwu.bookflow.representation.autor.CriarAutorRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;

    public AutorRepresentation criar(CriarAutorRepresentation request){
        Autor autor = autorMapper.toEntity(request);
        Autor autorSalvo = autorRepository.save(autor);

        return autorMapper.toRepresentation(autorSalvo);
    }

    public List<AutorRepresentation> listar(){
        return autorRepository.findAll()
                .stream()
                .map(autorMapper::toRepresentation)
                .toList();
    }

    public AutorRepresentation buscarPorId(Long id){
        Autor autor = autorRepository
                .findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException(id));

        return autorMapper.toRepresentation(autor);
    }

    public void deletar(Long id){
        if (!autorRepository.existsById(id)){
            throw new AutorNaoEncontradoException(id);
        }
        autorRepository.deleteById(id);
    }

    public AutorRepresentation atualizar(Long id, AtualizaAutorRepresentation request){
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException(id));

        autorMapper.updateEntity(request, autor);

        Autor autorAtualizado  = autorRepository.save(autor);

        return autorMapper.toRepresentation(autorAtualizado);
    }

}
