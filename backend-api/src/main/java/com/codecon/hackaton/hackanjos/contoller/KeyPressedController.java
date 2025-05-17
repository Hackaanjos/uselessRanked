package com.codecon.hackaton.hackanjos.contoller;

import com.codecon.hackaton.hackanjos.dto.request.KeyPressedRequestDTO;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.repository.UserRepository;
import com.codecon.hackaton.hackanjos.service.KeyPressedService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/keypressed")
@AllArgsConstructor
public class KeyPressedController {

    KeyPressedService keyPressedService;
    UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody List<KeyPressedRequestDTO> keyPressedRequestDTOList) {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        for (KeyPressedRequestDTO keyPressedRequestDTO : keyPressedRequestDTOList) {
            keyPressedService.saveOrUpdateEvent(keyPressedRequestDTO.getKeyCode(), keyPressedRequestDTO.getCount(), user);
        }

        return ResponseEntity.ok("KeyPresseds salvos com sucesso");
    }
}
