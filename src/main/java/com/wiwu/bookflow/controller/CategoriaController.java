package com.wiwu.bookflow.controller;

import com.wiwu.bookflow.representation.categoria.AtualizarCategoriaRepresentation;
import com.wiwu.bookflow.representation.categoria.CategoriaRepresentation;
import com.wiwu.bookflow.representation.categoria.CriarCategoriaRepresentation;
import com.wiwu.bookflow.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    public CategoriaRepresentation criar(@RequestBody @Valid CriarCategoriaRepresentation request){
        return categoriaService.criar(request);
    }

    @GetMapping
    public List<CategoriaRepresentation> listar(){
        return categoriaService.listar();
    }

    @GetMapping("/{id}")
    public CategoriaRepresentation buscarPorId(@PathVariable Long id) {
        return  categoriaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        categoriaService.deletar(id);
    }

    @PutMapping("/{id}")
    public CategoriaRepresentation atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarCategoriaRepresentation request
            ){
        return  categoriaService.atualizar(id, request);
    }



}
