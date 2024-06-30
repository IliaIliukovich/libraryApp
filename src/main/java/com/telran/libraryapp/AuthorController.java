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

//    @GetMapping("/find")
//    public List<Author> getAuthorByName(@RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
//        List<Author> list;
//        if (name == null) {
//            list = Author.authorList.stream().filter(author -> author.getSurname().equalsIgnoreCase(surname)).toList();
//
//        } else if (surname == null) {
//            list = Author.authorList.stream().filter(author -> author.getName().equalsIgnoreCase(name)).toList();
//
//        } else {
//            list = Author.authorList;
//        }
//        return list;
//
//    }



}
