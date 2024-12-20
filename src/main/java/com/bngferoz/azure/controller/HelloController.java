package com.bngferoz.azure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "HelloController", description = "A simple controller that returns a greeting.")
public class HelloController {

    @GetMapping("/greet")
    @Operation(summary = "Greeting", description = "Returns a greeting message")
    public String greet() {
        return "Hello from Spring Boot!";
    }
}
