package com.example.ksiazki.controller;


import com.example.ksiazki.model.Book;
import com.example.ksiazki.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class BookController {

    private BookRepository bookRepository;


    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    @GetMapping("books")
    public Iterable<Book> findAll() {
        return bookRepository.findAll();

    }

    @PostMapping("books")
    public Book createBook(@RequestParam String title,
                           @RequestParam String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        return bookRepository.save(book);

    }

    @PutMapping("books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestParam String title, @RequestParam String author) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(title);
            book.setAuthor(author);
            return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

        @DeleteMapping("books/{id}")
        public ResponseEntity<Book> deleteBook(@PathVariable Integer id){
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                bookRepository.delete(book.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
    }
