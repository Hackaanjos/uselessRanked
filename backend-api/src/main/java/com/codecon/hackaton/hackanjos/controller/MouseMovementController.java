package com.codecon.hackaton.hackanjos.controller;

import com.codecon.hackaton.hackanjos.dto.request.MouseMovementRequestDTO;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.repository.UserRepository;
import com.codecon.hackaton.hackanjos.service.MouseMovementService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mousemovement")
@AllArgsConstructor
public class MouseMovementController {

    MouseMovementService mouseMovementService;
     UserRepository userRepository;

     @PostMapping()
     public ResponseEntity<String> save(@RequestBody MouseMovementRequestDTO requestDTO, @AuthenticationPrincipal OAuth2User oAuth2User) {
         String email = oAuth2User.getAttribute("email");
         User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

         mouseMovementService.saveOrUpdateEvent(requestDTO.getDistance(), user);

         return ResponseEntity.ok("Eventos de movimento do mouse salvos com sucesso");
     }
}
