package com.qm.quantitymeasurement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("conversion-service is running perfectly!");
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> headHealth() {
        return ResponseEntity.ok().build();
    }
}
