package com.bngferoz.azure.controller;

import com.bngferoz.azure.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-test")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/fetch")
    public void fetchItems() {
        apiService.fetchItems();
    }

    @GetMapping("/create")
    public void createItem() {
        apiService.createItem();
    }
}
