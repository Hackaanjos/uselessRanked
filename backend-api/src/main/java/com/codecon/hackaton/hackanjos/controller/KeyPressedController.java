package com.codecon.hackaton.hackanjos.controller;

import com.codecon.hackaton.hackanjos.dto.request.KeyPressedRequestDTO;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.repository.UserRepository;
import com.codecon.hackaton.hackanjos.service.KeyPressedService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/keypressed")
@AllArgsConstructor
public class KeyPressedController {

    KeyPressedService keyPressedService;
    UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody List<KeyPressedRequestDTO> keyPressedRequestDTOList, @AuthenticationPrincipal OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        for (KeyPressedRequestDTO keyPressedRequestDTO : keyPressedRequestDTOList) {
            System.out.println(keyPressedRequestDTO.getKeyCode());
            System.out.println(keyPressedRequestDTO.getCount());
            keyPressedService.saveOrUpdateEvent(keyPressedRequestDTO.getKeyCode(), keyPressedRequestDTO.getCount(), user);
        }

        return ResponseEntity.ok("KeyPresseds salvos com sucesso");
    }
}
