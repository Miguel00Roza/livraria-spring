package com.miguel.livraria.service;

import com.miguel.livraria.domain.Publisher;
import com.miguel.livraria.exception.BadRequestException;
import com.miguel.livraria.exception.NotFoundException;
import com.miguel.livraria.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherById(Long id) {
        if (id <= 0) {
            throw new BadRequestException("ID must be greater than 0");
        }

        return publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Publisher not found"));
    }

    public Publisher create(Publisher publisher) {
        if (publisher.getName() == null || publisher.getName().isBlank()) {
            throw new BadRequestException("The publisher name cannot be empty");
        }

        return publisherRepository.save(publisher);
    }

}
