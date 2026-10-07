package com.wiwu.bookflow.service;

import com.wiwu.bookflow.entity.Exemplar;
import com.wiwu.bookflow.entity.Livro;
import com.wiwu.bookflow.entity.Reserva;
import com.wiwu.bookflow.entity.Usuario;
import com.wiwu.bookflow.event.ReservaCriadaEvent;
import com.wiwu.bookflow.exception.exemplar.ExemplarNaoEncontradoException;
import com.wiwu.bookflow.exception.reserva.ReservaNaoEncontradaException;
import com.wiwu.bookflow.exception.usuario.UsuarioNaoEncontradoException;
import com.wiwu.bookflow.mapper.ReservaMapper;
import com.wiwu.bookflow.repository.ExemplarRepository;
import com.wiwu.bookflow.repository.LivroRepository;
import com.wiwu.bookflow.repository.ReservaRepository;
import com.wiwu.bookflow.repository.UsuarioRepository;
import com.wiwu.bookflow.representation.reserva.CriarReservaRepresentation;
import com.wiwu.bookflow.representation.reserva.ReservaRepresentation;
import lombok.RequiredArgsConstructor;
import com.wiwu.bookflow.service.kafka.KafkaProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


    @Service
    @RequiredArgsConstructor
    public class ReservaService {

        private final ReservaRepository reservaRepository;
        private final UsuarioRepository usuarioRepository;
        private final ExemplarRepository exemplarRepository;
        private final ReservaMapper reservaMapper;
        private final KafkaProducer kafkaProducer;

        public ReservaRepresentation criar(
                CriarReservaRepresentation request) {

            if (reservaRepository.existsByUsuarioIdAndExemplarId(request.usuarioId(), request.exemplarId())){
                throw new IllegalStateException("Usuario ja possui reserva para este exemplar");
            }

            Usuario usuario = usuarioRepository
                    .findById(request.usuarioId())
                    .orElseThrow(
                            () -> new UsuarioNaoEncontradoException(
                                    request.usuarioId()
                            )
                    );

            Exemplar exemplar = exemplarRepository
                    .findById(request.exemplarId())
                    .orElseThrow(
                            () -> new ExemplarNaoEncontradoException(
                                    request.exemplarId()
                            )
                    );

            Reserva reserva =
                    reservaMapper.toEntity(request);

            reserva.setUsuario(usuario);
            reserva.setExemplar(exemplar);
            reserva.setDataReserva(LocalDateTime.now());

            Reserva reservaSalva =
                    reservaRepository.save(reserva);

            kafkaProducer.publicarReservaCriada(
                    new ReservaCriadaEvent(
                            reservaSalva.getId(),
                            usuario.getId(),
                            exemplar.getId()
                    )
            );

            return reservaMapper.toRepresentation(
                    reservaSalva
            );
        }

        public List<ReservaRepresentation> listar() {

            return reservaRepository.findAll()
                    .stream()
                    .map(reservaMapper::toRepresentation)
                    .toList();
        }

        public ReservaRepresentation buscarPorId(Long id) {

            Reserva reserva = reservaRepository
                    .findById(id)
                    .orElseThrow(
                            () -> new ReservaNaoEncontradaException(id)
                    );

            return reservaMapper.toRepresentation(reserva);
        }

        public void deletar(Long id) {

            if (!reservaRepository.existsById(id)) {
                throw new ReservaNaoEncontradaException(id);
            }

            reservaRepository.deleteById(id);
        }
    }


