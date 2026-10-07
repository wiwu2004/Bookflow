package com.wiwu.bookflow.controller;

import com.wiwu.bookflow.representation.emprestimo.CriarEmprestimoRepresentation;
import com.wiwu.bookflow.representation.emprestimo.EmprestimoRepresentation;
import com.wiwu.bookflow.service.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService service;

    @PostMapping
    public EmprestimoRepresentation criar(
            @RequestBody @Valid CriarEmprestimoRepresentation representation
            ){
        return service.criar(representation);
    }

    @GetMapping
    public List<EmprestimoRepresentation> buscarPorId(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public EmprestimoRepresentation listarPorId(@PathVariable @Valid Long id){
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable @Valid Long id){
        service.deletar(id);
    }

    @PatchMapping("/{id}/devolver")
    public EmprestimoRepresentation devolver(@PathVariable Long id){
        return service.devolver(id);
    }


}
