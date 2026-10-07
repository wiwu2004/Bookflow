package com.wiwu.bookflow.controller;

import com.wiwu.bookflow.representation.autor.AtualizaAutorRepresentation;
import com.wiwu.bookflow.representation.autor.AutorRepresentation;
import com.wiwu.bookflow.representation.autor.CriarAutorRepresentation;
import com.wiwu.bookflow.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {
    private final AutorService autorService;

    @PostMapping
    public AutorRepresentation criar(@RequestBody @Valid CriarAutorRepresentation request){
        return autorService.criar(request);
    }

    @GetMapping
    public List<AutorRepresentation> listar(){
        return autorService.listar();
    }

    @GetMapping("/{id}")
    public AutorRepresentation buscarPorId(@PathVariable Long id){
        return autorService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        autorService.deletar(id);
    }

    @PutMapping("/{id}")
    public AutorRepresentation atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaAutorRepresentation request){
        return autorService.atualizar(id,request);
    }
}
