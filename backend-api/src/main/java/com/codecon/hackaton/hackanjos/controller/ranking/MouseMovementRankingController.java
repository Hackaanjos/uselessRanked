package com.codecon.hackaton.hackanjos.controller.ranking;

import com.codecon.hackaton.hackanjos.dto.response.ranking.MouseMovementResponseDTO;
import com.codecon.hackaton.hackanjos.model.enums.IntervalFilter;
import com.codecon.hackaton.hackanjos.service.MouseMovementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ranking/mousemovement")
@AllArgsConstructor
public class MouseMovementRankingController {

    MouseMovementService mouseMovementService;

    @GetMapping("/{intervalFilterString}")
    public ResponseEntity<Page<MouseMovementResponseDTO>> listAll(
            @PathVariable String intervalFilterString,
            @PageableDefault(page = 0, size = 5) Pageable pageable) {

        IntervalFilter intervalFilter = IntervalFilter.fromString(intervalFilterString);

        return ResponseEntity.ok(mouseMovementService.listAll(intervalFilter, pageable));
    }
}
