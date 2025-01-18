package com.example;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(App.class, args);
        var repo = context.getBean(BookRepository.class);
        List<Book> books = List.of(
                new Book(null, "Don Quijote de la Mancha", "Miguel de Cervantes", 20.0),
                new Book(null, "La casa de los espíritus", "Isabel Allende", 15.0),
                new Book(null, "Amor en tiempos de cólera", "Gabriel García Márquez", 18.0),
                new Book(null, "El Principito", "Antoine Saint-Exupéry", 10.0)
        );
        repo.saveAll(books);
    }

}
