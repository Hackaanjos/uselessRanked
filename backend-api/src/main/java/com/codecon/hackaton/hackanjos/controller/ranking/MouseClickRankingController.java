package com.codecon.hackaton.hackanjos.controller.ranking;

import com.codecon.hackaton.hackanjos.dto.reponse.ranking.MouseClickDTO;
import com.codecon.hackaton.hackanjos.model.enums.IntervalFilter;
import com.codecon.hackaton.hackanjos.service.MouseClickService;

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
@RequestMapping("api/ranking/mouseclick")
@AllArgsConstructor
public class MouseClickRankingController {

    private MouseClickService mouseClickService;

    @GetMapping("/{intervalFilterString}")
    public ResponseEntity<Page<MouseClickDTO>> listAll(
            @PathVariable String intervalFilterString,
            @PageableDefault(page = 0, size = 5) Pageable pageable) {

        IntervalFilter intervalFilter = IntervalFilter.fromString(intervalFilterString);

        return ResponseEntity.ok(mouseClickService.listAll(intervalFilter, pageable));
    }
}
