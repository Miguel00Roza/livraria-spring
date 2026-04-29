package com.miguel.livraria.repository;

import com.miguel.livraria.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
