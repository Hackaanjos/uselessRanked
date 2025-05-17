package com.codecon.hackaton.hackanjos.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    @GetMapping()
    public ResponseEntity<Map<String, Object>> index(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String message) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && 
                                authentication.isAuthenticated() && 
                                !"anonymousUser".equals(authentication.getPrincipal());

        if (isAuthenticated && authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            return ResponseEntity.ok(Map.of(
                "message", "Olá, " + oAuth2User.getAttribute("name") + "!",
                "isAuthenticated", true,
                "user", Map.of(
                    "name", oAuth2User.getAttribute("name")
                ),
                "error", error != null ? error : "",
                "success", message != null ? message : ""
            ));
        }

        return ResponseEntity.ok(Map.of(
            "message", "Olá mundo!",
            "isAuthenticated", false,
            "error", error != null ? error : "",
            "success", message != null ? message : ""
        ));
    }
}
