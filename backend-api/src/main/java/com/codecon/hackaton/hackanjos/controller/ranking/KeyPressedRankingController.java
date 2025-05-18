package com.codecon.hackaton.hackanjos.controller.ranking;

import com.codecon.hackaton.hackanjos.dto.response.AllKeyPressedResponseDTO;
import com.codecon.hackaton.hackanjos.dto.response.KeyPressedByKeyResponseDTO;
import com.codecon.hackaton.hackanjos.model.enums.IntervalFilter;
import com.codecon.hackaton.hackanjos.service.KeyPressedService;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ranking/keypressed")
@AllArgsConstructor
public class KeyPressedRankingController {

    private KeyPressedService keyPressedService;

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
