package com.wiwu.bookflow.controller;

import com.wiwu.bookflow.representation.usuario.AtualizarUsuarioRepresentation;
import com.wiwu.bookflow.representation.usuario.CriarUsuarioRepresentation;
import com.wiwu.bookflow.representation.usuario.UsuarioRepresentation;
import com.wiwu.bookflow.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public UsuarioRepresentation criar(@RequestBody @Valid CriarUsuarioRepresentation representation){
        return usuarioService.criar(representation);
    }

    @GetMapping
    public List<UsuarioRepresentation> listar(){
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public  UsuarioRepresentation listarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public  UsuarioRepresentation atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarUsuarioRepresentation representation){
        return usuarioService.atualizar(id, representation);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        usuarioService.deletar(id);
    }
}