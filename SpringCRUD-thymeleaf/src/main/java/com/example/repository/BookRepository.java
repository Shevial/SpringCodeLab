package com.example.repository;

import com.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// repositorio nos permite interactuar con la base de datos
@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
}
