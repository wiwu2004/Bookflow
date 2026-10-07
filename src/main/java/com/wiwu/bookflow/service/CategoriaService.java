package com.wiwu.bookflow.service;

import com.wiwu.bookflow.entity.Categoria;
import com.wiwu.bookflow.exception.categoria.CategoriaNaoEncontradaException;
import com.wiwu.bookflow.mapper.CategoriaMapper;
import com.wiwu.bookflow.repository.CategoriaRepository;
import com.wiwu.bookflow.representation.categoria.AtualizarCategoriaRepresentation;
import com.wiwu.bookflow.representation.categoria.CategoriaRepresentation;
import com.wiwu.bookflow.representation.categoria.CriarCategoriaRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;


    public CategoriaRepresentation criar(
            CriarCategoriaRepresentation request
    ){
        Categoria categoria = categoriaMapper.toEntity(request);
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        return categoriaMapper.toRepresentation(categoriaSalva);
    }

    public List<CategoriaRepresentation> listar(){
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toRepresentation)
                .toList();
    }

    public CategoriaRepresentation buscarPorId(Long id){
        Categoria categoria = categoriaRepository
                .findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));

        return categoriaMapper.toRepresentation(categoria);
    }

    public void deletar(Long id){
        if (!categoriaRepository.existsById(id)){
            throw new CategoriaNaoEncontradaException(id);
        }
        categoriaRepository.deleteById(id);
    }

   public CategoriaRepresentation atualizar(
           Long id,
           AtualizarCategoriaRepresentation request
   ){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()-> new CategoriaNaoEncontradaException(id));

        categoriaMapper.updateEntity(request, categoria);

        Categoria categoriaAtualizada =  categoriaRepository.save(categoria);

        return categoriaMapper.toRepresentation(categoriaAtualizada);
   }



}
