package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Author;
import com.telran.libraryapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        Optional<Author> nameAuthor = authorService.getNameAuthorByID(id);
        if (nameAuthor.isPresent()) {
            return new ResponseEntity<>(nameAuthor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        authorService.add(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        boolean isUpdated = authorService.updateAuthor(author);
        if (isUpdated) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(author, HttpStatus.CREATED);
        }

    }

    @DeleteMapping
    public ResponseEntity<Author> deleteAuthor(@RequestParam int id) {
        authorService.removeAuthor(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/findAuthorByName")
    public List<Author> getAuthorByName(@RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
        return authorService.returnAuthorByNameOrSurname(name, surname);
    }

    @GetMapping("/findAuthorByRandomWord")
    public ResponseEntity<List<Author>> getAuthorByRandomWord(@RequestParam String randomWord) {
        List<Author> authors = authorService.getAuthorByRandomWord(randomWord);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }


}
