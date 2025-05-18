package com.codecon.hackaton.hackanjos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class CustomErrorController implements ErrorController {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro Interno do Servidor",
                "Ocorreu um erro inesperado"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "Não Encontrado",
                "O recurso solicitado não foi encontrado"
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @GetMapping("/api/error/unauthorized")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Map<String, Object>> unauthorized() {
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Não Autorizado",
                "Você precisa estar autenticado para acessar este recurso"
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    @GetMapping("/api/error/forbidden")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Map<String, Object>> forbidden() {
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.FORBIDDEN,
                "Acesso Negado",
                "Você não tem permissão para acessar este recurso"
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    @GetMapping("/api/error/not-found")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> notFound() {
        Map<String, Object> body = buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "Não Encontrado",
                "O recurso solicitado não foi encontrado"
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    private Map<String, Object> buildErrorResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", dateFormat.format(new Date()));
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);

        return body;
    }
}
