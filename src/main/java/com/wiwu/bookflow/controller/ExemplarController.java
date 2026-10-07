package com.wiwu.bookflow.controller;

import com.wiwu.bookflow.representation.exemplar.CriarExemplarRepresentation;
import com.wiwu.bookflow.representation.exemplar.ExemplarRepresentation;
import com.wiwu.bookflow.service.ExemplarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exemplares")
@RequiredArgsConstructor
public class ExemplarController {

    private final ExemplarService exemplarService;

    @PostMapping
    public ExemplarRepresentation criar(@RequestBody @Valid CriarExemplarRepresentation representation){
    return exemplarService.criar(representation);
    }

    @GetMapping
    public List<ExemplarRepresentation> listar(){
        return exemplarService.listar();
    }

    @GetMapping("/{id}")
    public ExemplarRepresentation buscarPorId(@PathVariable Long id){
        return exemplarService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void  deletar(@PathVariable Long id){
        exemplarService.deletar(id);
    }

}
