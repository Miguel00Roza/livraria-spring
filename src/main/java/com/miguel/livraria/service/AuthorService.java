package com.miguel.livraria.service;

import com.miguel.livraria.domain.Author;
import com.miguel.livraria.exception.BadRequestException;
import com.miguel.livraria.exception.NotFoundException;
import com.miguel.livraria.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        if (id <= 0) {
            throw new BadRequestException("ID must be greater than 0");
        }

        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));
    }

    public Author create(Author author) {
        if (author.getName() == null || author.getName().isBlank()) {
            throw new BadRequestException("The author name cannot be empty");
        }

        return authorRepository.save(author);
    }

}
