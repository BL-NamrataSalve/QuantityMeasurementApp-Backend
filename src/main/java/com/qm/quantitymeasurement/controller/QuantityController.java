package com.qm.quantitymeasurement.controller;

import com.qm.quantitymeasurement.dto.QuantityRequestDto;
import com.qm.quantitymeasurement.dto.QuantityResponseDto;
import com.qm.quantitymeasurement.service.QuantityService;
import com.qm.quantitymeasurement.service.impl.QuantityServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
@CrossOrigin(origins = "*")
public class QuantityController {

    private static final Logger log = LoggerFactory.getLogger(QuantityController.class);

    private final QuantityService service;

    @Autowired
    public QuantityController(QuantityService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public QuantityResponseDto add(@Valid @RequestBody QuantityRequestDto input) {
        log.info("Received request to add quantities: {}", input);
        return service.add(input);
    }

    @PostMapping("/subtract")
    public QuantityResponseDto subtract(@Valid @RequestBody QuantityRequestDto input) {
        log.info("Received request to subtract quantities: {}", input);
        return service.subtract(input);
    }

    @PostMapping("/divide")
    public Double divide(@Valid @RequestBody QuantityRequestDto input) {
        log.info("Received request to divide quantity: {}", input);
        return service.divide(input);
    }

    @PostMapping("/convert")
    public QuantityResponseDto convert(@Valid @RequestBody QuantityRequestDto input) {
        log.info("Received request to convert quantity: {}", input);
        return service.convert(input);
    }

    @PostMapping("/compare")
    public QuantityResponseDto compare(@Valid @RequestBody QuantityRequestDto input) {
        log.info("Received request to compare quantities: {}", input);
        return service.compare(input);
    }

    @GetMapping("/history")
    public List<?> history() {
        log.info("Received request to fetch operation history");
        return service.getHistory();
    }

    @GetMapping("/history/{operation}")
    public List<?> historyByOperation(@PathVariable String operation) {
        log.info("Received request to fetch operation history for: {}", operation);
        return service.getByOperation(operation.toUpperCase());
    }
}