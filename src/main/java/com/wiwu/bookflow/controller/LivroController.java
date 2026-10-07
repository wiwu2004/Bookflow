package com.wiwu.bookflow.controller;

import com.wiwu.bookflow.representation.livro.AtualizarLivroRepresentation;
import com.wiwu.bookflow.representation.livro.CriarLivroRepresentation;
import com.wiwu.bookflow.representation.livro.LivroRepresentation;
import com.wiwu.bookflow.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public LivroRepresentation criar(@RequestBody @Valid CriarLivroRepresentation request){
        return livroService.criar(request);
    }

    @GetMapping
    public List<LivroRepresentation> listar(){
        return livroService.listar();
    }

    @GetMapping("/{id}")
    public LivroRepresentation listarPorId(@PathVariable Long id){
        return livroService.listarPorId(id);
    }

    @PutMapping("{id}")
    public LivroRepresentation atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarLivroRepresentation request){
        return livroService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        livroService.deletar(id);
    }


}
