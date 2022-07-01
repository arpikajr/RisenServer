package com.risen.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risen.dto.SubRisenDto;
import com.risen.service.SubRisenService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/subrisen")
@AllArgsConstructor
@Slf4j
public class SubRisenController {

    private final SubRisenService subRisenService;

    @PostMapping
    public ResponseEntity<SubRisenDto> createSubRisen(@RequestBody SubRisenDto subRisenDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subRisenService.save(subRisenDto));
    }

    @GetMapping
    public ResponseEntity<List<SubRisenDto>> getAllSubRisens() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subRisenService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubRisenDto> getSubRisen(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subRisenService.getSubRisen(id));
    }
}