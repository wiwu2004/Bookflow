package com.wiwu.bookflow.service;

import com.wiwu.bookflow.entity.Autor;
import com.wiwu.bookflow.entity.Categoria;
import com.wiwu.bookflow.entity.Livro;
import com.wiwu.bookflow.exception.autor.AutorNaoEncontradoException;
import com.wiwu.bookflow.exception.categoria.CategoriaNaoEncontradaException;
import com.wiwu.bookflow.exception.livro.LivroNaoEncontradoException;
import com.wiwu.bookflow.mapper.LivroMapper;
import com.wiwu.bookflow.repository.AutorRepository;
import com.wiwu.bookflow.repository.CategoriaRepository;
import com.wiwu.bookflow.repository.LivroRepository;
import com.wiwu.bookflow.representation.livro.AtualizarLivroRepresentation;
import com.wiwu.bookflow.representation.livro.CriarLivroRepresentation;
import com.wiwu.bookflow.representation.livro.LivroRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final LivroMapper livroMapper;

    public LivroRepresentation criar(CriarLivroRepresentation request){
        Autor autor = autorRepository.findById(request.autorId()).orElseThrow(() -> new AutorNaoEncontradoException(request.autorId()));

        Categoria categoria = categoriaRepository.findById(request.categoriaId()).orElseThrow(() -> new CategoriaNaoEncontradaException(request.categoriaId()));

        Livro livro = livroMapper.toEntity(request);

        livro.setAutor(autor);
        livro.setCategoria(categoria);

        Livro livroSalvo = livroRepository.save(livro);

        return livroMapper.toRepresentation(livroSalvo);
    }

    public List<LivroRepresentation> listar(){
        return livroRepository.findAll()
                .stream()
                .map(livroMapper::toRepresentation)
                .toList();
    }

    public LivroRepresentation listarPorId(Long id){
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new LivroNaoEncontradoException(id));
        return livroMapper.toRepresentation(livro);
    }

    public LivroRepresentation atualizar(Long id,AtualizarLivroRepresentation request){
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        Autor autor = autorRepository.findById(request.autorId()).orElseThrow();

        Categoria categoria = categoriaRepository.findById(request.categoriaId()).orElseThrow();

        livroMapper.updateEntity(request, livro);

        livro.setAutor(autor);
        livro.setCategoria(categoria);

        Livro livroAtualizado = livroRepository.save(livro);

        return livroMapper.toRepresentation(livroAtualizado);
    }

    public void deletar(Long id) {

        if (!livroRepository.existsById(id)) {
            throw new LivroNaoEncontradoException(id);
        }

        livroRepository.deleteById(id);
    }
}