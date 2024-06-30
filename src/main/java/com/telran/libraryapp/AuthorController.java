package com.telran.libraryapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @GetMapping
    public List<Author> getAuthors() {
        return Author.authorList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        Optional<Author> nameAuthor = Author.authorList.stream().filter(author -> author.getId() == id).findFirst();
        if (nameAuthor.isPresent()) {
            return new ResponseEntity<>(nameAuthor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author.authorList.add(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        if (Author.authorList.contains(author)) {
            int index = Author.authorList.indexOf(author);
            Author.authorList.set(index, author);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } else {
            Author.authorList.add(author);
            return new ResponseEntity<>(author, HttpStatus.CREATED);
        }

    }

    @DeleteMapping
    public ResponseEntity<Author> deleteAuthor(@RequestParam int id) {
        Author.authorList.removeIf(author1 -> author1.getId() == id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/findAuthorByName")
    public List<Author> getAuthorByName(@RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
        if (name != null && surname != null) {
            return Author.authorList.stream().filter(author -> author.getSurname().startsWith(surname) && author.getName().startsWith(name)).toList();
        } else if (name != null && surname == null) {
            return Author.authorList.stream().filter(author -> author.getName().startsWith(name)).toList();
        } else if (name == null && surname != null) {
            return Author.authorList.stream().filter(author -> author.getSurname().startsWith(surname)).toList();
        } else {
            return Author.authorList;
        }

    }

    @GetMapping("/findAuthorByRandomWord")
    public ResponseEntity<Author> getAuthorByRandomWord(@RequestParam String randomWord) {
        Optional<Author> authorByRandomWord = Author.authorList.stream().filter(author -> author.getName().contains(randomWord) ||
                author.getSurname().contains(randomWord) ||
                author.getAuthorInfo().contains(randomWord)).findAny();
        return new ResponseEntity<>(authorByRandomWord.get(),HttpStatus.OK);
    }


}
