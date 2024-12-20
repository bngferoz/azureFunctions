package com.bngferoz.azure.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    private final RestTemplate restTemplate;

    // Base URL of the API
    private static final String API_BASE_URL = "https://jsonplaceholder.typicode.com";

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Perform a GET request
    public void fetchItems() {
        String url = API_BASE_URL + "/posts";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            logger.info("GET Response: {}", response.getBody());
        } catch (Exception e) {
            logger.error("Error fetching items: {}", e.getMessage());
        }
    }

    // Perform a POST request
    public void createItem() {
        String url = API_BASE_URL + "/posts";

        // Define the request body
        String requestBody = """
                {
                    "title": "foo",
                    "body": "bar",
                    "userId": 1
                }
                """;

        // Set headers (e.g., Content-Type)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            logger.info("POST Response: {}", response.getBody());
        } catch (Exception e) {
            logger.error("Error creating item: {}", e.getMessage());
        }
    }
}
