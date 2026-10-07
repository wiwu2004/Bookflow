package com.wiwu.bookflow.service;

import com.wiwu.bookflow.entity.Usuario;
import com.wiwu.bookflow.exception.usuario.UsuarioNaoEncontradoException;
import com.wiwu.bookflow.mapper.UsuarioMapper;
import com.wiwu.bookflow.repository.UsuarioRepository;
import com.wiwu.bookflow.representation.usuario.AtualizarUsuarioRepresentation;
import com.wiwu.bookflow.representation.usuario.CriarUsuarioRepresentation;
import com.wiwu.bookflow.representation.usuario.UsuarioRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRepresentation criar(CriarUsuarioRepresentation representation){
        Usuario usuario = usuarioMapper.toEntity(representation);

        Usuario usuarioSalvo = repository.save(usuario);

        return usuarioMapper.toRepresentation(usuarioSalvo);
    }

    public List<UsuarioRepresentation> listar(){
        return repository.findAll()
                .stream()
                .map(usuarioMapper::toRepresentation)
                .toList();
    }

    public UsuarioRepresentation buscarPorId(Long id){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        return usuarioMapper.toRepresentation(usuario);
    }

    public UsuarioRepresentation atualizar(Long id, AtualizarUsuarioRepresentation representation){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        usuarioMapper.updateEntity(representation, usuario);

        Usuario usuarioSalvo = repository.save(usuario);

        return usuarioMapper.toRepresentation(usuarioSalvo);
    }

    public void deletar(Long id){
        if (!repository.existsById(id)){
            throw new UsuarioNaoEncontradoException(id);
        }
        repository.deleteById(id);
    }

}
