package com.qm.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/")
public class HealthCheckController {

    @GetMapping
    public String health() {
        return "api-gateway is running perfectly!";
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public void headHealth() {
        // Return 200 OK for HEAD request (used by Render health check)
    }
}
