package com.bngferoz.azure.controller;


import com.bngferoz.azure.properties.AppProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wow")
@Tag(name = "WowController", description = "WowController")
public class WowController {

    private static final String WOW = "wow";
    private static final List<String> items = new ArrayList<>();

    private final AppProperties appProperties;


    public WowController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }


    @RequestMapping("/hello")
    @Operation(summary = "Hello", description = "Hello")
    public String hello() {
        return WOW;
    }

    @RequestMapping("/items")
    public List<String> items() {
        return items;
    }

    @RequestMapping("/items/add")
    public ResponseEntity<List<String>> addItem(String item) {
        items.add(item);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/items/clear")
    public ResponseEntity<List<String>> clearItems() {
        items.clear();
        return ResponseEntity.ok(items);
    }



    @PostMapping("/items")
    public ResponseEntity<List<String>> addItems(@RequestBody List<String> items) {
        this.items.addAll(items);
        return ResponseEntity.ok(this.items);
    }

    @PostMapping("/singleItemAdd")
    public ResponseEntity<List<String>> addSingleItem(@RequestBody String item) {
        items.add(item);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/item/{index}")
    public ResponseEntity<List<String>> updateItemByIndex(@PathVariable int index, @RequestBody String item) {
        if (index < items.size()) {
            items.set(index, item);
        }
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/items/{index}")
    public ResponseEntity<List<String>> deleteItemByIndex(@PathVariable int index) {
        if (index < items.size()) {
            items.remove(index);
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchItems(@RequestParam String search) {
        List<String> result = new ArrayList<>();
        for (String item : items) {
            if (item.contains(search)) {
                result.add(item);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/error")
    public ResponseEntity<String> error() {
        throw new RuntimeException("Error!");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @GetMapping("/info")
    public ResponseEntity<AppProperties> getAppProperties() {
        return ResponseEntity.ok(appProperties);
    }

}

