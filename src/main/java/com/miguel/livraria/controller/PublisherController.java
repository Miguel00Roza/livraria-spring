package com.miguel.livraria.controller;

import com.miguel.livraria.domain.Publisher;
import com.miguel.livraria.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public List<Publisher> getAll() {
        return publisherService.getAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Publisher getPublisherById(@PathVariable long id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    public Publisher create(@RequestBody Publisher publisher) {
        return publisherService.create(publisher);
    }

}
