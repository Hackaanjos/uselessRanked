package com.codecon.hackaton.hackanjos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Redirecionando para o login do Google...");
    }

    @GetMapping("/success")
    public ResponseEntity<Map<String, Object>> success(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Usuário não autenticado"));
        }

        return ResponseEntity.ok(Map.of(
            "email", principal.getAttribute("email"),
            "name", principal.getAttribute("name"),
            "picture", principal.getAttribute("picture")
        ));
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Usuário não autenticado"));
        }

        return ResponseEntity.ok(Map.of(
            "email", principal.getAttribute("email"),
            "name", principal.getAttribute("name"),
            "picture", principal.getAttribute("picture")
        ));
    }
}
