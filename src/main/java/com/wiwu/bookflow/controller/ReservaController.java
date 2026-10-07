package com.wiwu.bookflow.controller;

import com.wiwu.bookflow.representation.reserva.CriarReservaRepresentation;
import com.wiwu.bookflow.representation.reserva.ReservaRepresentation;
import com.wiwu.bookflow.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ReservaRepresentation criar(
            @RequestBody @Valid CriarReservaRepresentation request) {

        return reservaService.criar(request);
    }

    @GetMapping
    public List<ReservaRepresentation> listar() {

        return reservaService.listar();
    }

    @GetMapping("/{id}")
    public ReservaRepresentation buscarPorId(
            @PathVariable Long id) {

        return reservaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(
            @PathVariable Long id) {

        reservaService.deletar(id);
    }
}