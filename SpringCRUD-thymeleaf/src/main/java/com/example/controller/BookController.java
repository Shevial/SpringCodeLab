package com.example.controller;

import com.example.repository.BookRepository;
import com.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// nos permite mover los datos del repository a la vista
@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

//RECUPERACION DE DATOS
    @GetMapping("/") // apunta a localhost8080
    public String index(){

        return "redirect:/books";
    }

    @GetMapping ("/books")
    public String findAll(Model model){
        model.addAttribute("books", repository.findAll());
        return "book-list";
    }

    @GetMapping ("/books/view/{id}")// recuperar en base a id (VISUALIZACION DE 1 libro en particular
    public String findById(Model model, @PathVariable Long id){
        model.addAttribute("book", repository.findById(id).get());
        return "book-view";
    }

//EDICION DE DATOS
    @GetMapping("/books/edit/{id}")
    /*public String getFormWithBook(Model model, @PathVariable Long id){
        model.addAttribute("book", repository.findById(id).orElse(new Book()));
        return "book-form";
    }*/
    public String getFormWithBook(Model model, @PathVariable Long id){
        if(repository.existsById(id)) {
            repository.findById(id).ifPresent(b -> model.addAttribute("book", b));
            return "book-form";
        } else {
            return "redirect:/books/form";
        }
    }
//CREACION DE DATOS
    //para obtener el formulario
    @GetMapping("/books/form")
    //si existe un libro, cargalo en la pantalla , y llevame a esa pantalla, si no existe ningun libro para editar, llevame al formulario vacio
    public String getEmptyForm(Model model) {
        model.addAttribute("book", new Book()); return "book-form";
    }
    //para recibir los datos rellenados del formulario
    @PostMapping ("/books")
    public String create(@ModelAttribute Book book){ //mapeo entre formulario y controlador
        if(book.getId() != null){
                //actualizacion
                //si existe el id, edita, sino no se puede
            repository.findById(book.getId()).ifPresent(b -> {
                //modificar solo aquello que queramos
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                b.setPrice(book.getPrice());
                repository.save(b);
            });
        } else{
            //creacion
            repository.save(book); //guardar en BD

        }
        return "redirect:/books"; //Devolver una vista
    }

//BORRADO
    @GetMapping("/books/delete/{id}")
    public String deleteById(@PathVariable Long id){
        if(repository.existsById(id))
            repository.deleteById(id);
        return "redirect:/books";
    }

}
