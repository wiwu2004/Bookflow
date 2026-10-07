package com.wiwu.bookflow.exception;

import com.wiwu.bookflow.exception.categoria.CategoriaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleCategoriaNaoEncontrada(CategoriaNaoEncontradaException ex){
        ErrorResponse errorResponse =
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        null
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> erros = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> erros.put(error.getField(),error.getDefaultMessage()));
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação.",
                erros
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException exception){
        ErrorResponse response = new
                ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage(),
                        null
        );
        return ResponseEntity.badRequest().body(response);
    }

}
