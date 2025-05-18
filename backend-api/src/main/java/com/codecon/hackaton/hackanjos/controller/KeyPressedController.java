package com.codecon.hackaton.hackanjos.controller;

import com.codecon.hackaton.hackanjos.dto.response.AllKeyPressedResponseDTO;
import com.codecon.hackaton.hackanjos.dto.response.KeyPressedByKeyResponseDTO;
import com.codecon.hackaton.hackanjos.dto.request.KeyPressedRequestDTO;
import com.codecon.hackaton.hackanjos.model.User;
import com.codecon.hackaton.hackanjos.model.enums.IntervalFilter;
import com.codecon.hackaton.hackanjos.repository.UserRepository;
import com.codecon.hackaton.hackanjos.service.KeyPressedService;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/keypressed")
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

    @GetMapping("/{key}/findByKey/{intervalFilterString}")
    public ResponseEntity<Page<KeyPressedByKeyResponseDTO>> listByKey(
            @PathVariable String key,
            @PathVariable String intervalFilterString,
            @PageableDefault(page = 0, size = 5) Pageable pageable) {
        IntervalFilter intervalFilter = IntervalFilter.fromString(intervalFilterString);

        return ResponseEntity.ok(keyPressedService.listByKey(key.toUpperCase(), intervalFilter, pageable));
    }

    @GetMapping("/{intervalFilterString}")
    public ResponseEntity<Page<AllKeyPressedResponseDTO>> listAll(
            @PathVariable String intervalFilterString,
            @PageableDefault(page = 0, size = 5) Pageable pageable) {
        IntervalFilter intervalFilter = IntervalFilter.fromString(intervalFilterString);

        return ResponseEntity.ok(keyPressedService.listAll(intervalFilter, pageable));
    }
}
