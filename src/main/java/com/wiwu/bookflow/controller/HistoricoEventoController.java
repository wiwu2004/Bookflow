package com.wiwu.bookflow.controller;

import com.wiwu.bookflow.representation.historico.HistoricoEventoRepresentation;
import com.wiwu.bookflow.service.HistoricoEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historico-eventos")
@RequiredArgsConstructor
public class HistoricoEventoController {

    private final HistoricoEventoService historicoEventoService;

    @GetMapping
    public List<HistoricoEventoRepresentation> listar(
            @RequestParam(required = false)
            String tipoEvento) {

        return historicoEventoService.listar(
                tipoEvento
        );
    }
}
