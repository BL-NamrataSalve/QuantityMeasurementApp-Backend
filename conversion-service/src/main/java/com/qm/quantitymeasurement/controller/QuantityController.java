package com.qm.quantitymeasurement.controller;

import com.qm.quantitymeasurement.dto.QuantityDTO;
import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;
import com.qm.quantitymeasurement.entity.QuantityOperationEntity;
import com.qm.quantitymeasurement.service.QuantityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityController {

    private final QuantityService service;

    @Autowired
    public QuantityController(QuantityService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityResponseDto> add(@Valid @RequestBody QuantityRequestDto input) {
        log.info("Received request to add quantities: {}", input);
        return ResponseEntity.ok(service.add(input));
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityResponseDto> subtract(@Valid @RequestBody QuantityRequestDto input) {
        log.info("Received request to subtract quantities: {}", input);
        return ResponseEntity.ok(service.subtract(input));
    }

    @PostMapping("/divide")
    public ResponseEntity<Double> divide(@Valid @RequestBody QuantityRequestDto input) {
        log.info("Received request to divide quantity: {}", input);
        return ResponseEntity.ok(service.divide(input));
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityResponseDto> convert(@Valid @RequestBody QuantityRequestDto input) {
        log.info("Received request to convert quantity: {}", input);
        return ResponseEntity.ok(service.convert(input));
    }

    @PostMapping("/compare")
    public ResponseEntity<QuantityResponseDto> compare(@Valid @RequestBody QuantityRequestDto input) {
        log.info("Received request to compare quantities: {}", input);
        return ResponseEntity.ok(service.compare(input));
    }

    @GetMapping("/history")
    public ResponseEntity<List<?>> history() {
        log.info("Received request to fetch operation history");
        return ResponseEntity.ok(service.getHistory());
    }

    @GetMapping("/history/{operation}")
    public ResponseEntity<List<?>> historyByOperation(@PathVariable String operation) {
        log.info("Received request to fetch operation history for: {}", operation);
        return ResponseEntity.ok(service.getByOperation(operation.toUpperCase()));
    }
}
