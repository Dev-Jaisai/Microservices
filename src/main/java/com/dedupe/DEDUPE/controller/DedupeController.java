package com.dedupe.DEDUPE.controller;

import com.dedupe.DEDUPE.dto.DedupeDto;
import com.dedupe.DEDUPE.service.DedupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/dedupe")
public class DedupeController {

    @Autowired
    private DedupeService dedupeService;

    @GetMapping("/check")
    public ResponseEntity<DedupeDto> checkDedupe(@RequestParam String panCard) {
        DedupeDto dedupeResult = dedupeService.processUserData(panCard);

        if (dedupeResult != null) {
            return ResponseEntity.ok(dedupeResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

