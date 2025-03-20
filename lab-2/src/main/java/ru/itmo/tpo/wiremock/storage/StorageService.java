package ru.itmo.tpo.wiremock.storage;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StorageService {
    private final List<Object> storage = new ArrayList<>();

    @GetMapping
    public List<Object> getAllStorage() {
        return storage;
    }

    @PostMapping
    public void addObject(@RequestBody Object object) {
        storage.add(object);
    }

    @DeleteMapping("/clear")
    public void clearStorage() {
        storage.clear();
    }
}
