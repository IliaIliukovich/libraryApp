package com.telran.libraryapp.controller;

import com.telran.libraryapp.dto.AuthorDto;
import com.telran.libraryapp.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
@Validated
@Slf4j
@Tag(name = "Author Controller", description = "Actions with author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all author")
    public List<AuthorDto> getAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary ="Search by id")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        Optional<AuthorDto> authorOptional = authorService.getNameAuthorByID(id);
        if (authorOptional.isPresent()) {
            return new ResponseEntity<>(authorOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "add author")
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody @Valid AuthorDto author) {
        authorService.add(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "update author")
    public ResponseEntity<AuthorDto> updateAuthor(@RequestBody @Valid AuthorDto author) {
        boolean isUpdated = authorService.updateAuthor(author);
        return new ResponseEntity<>(author, isUpdated ? HttpStatus.OK : HttpStatus.CREATED);

    }

    @DeleteMapping
    @Operation(summary = "remove author")
    public ResponseEntity<AuthorDto> deleteAuthor(@RequestParam Long id) {
        authorService.removeAuthor(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/findAuthorByName")
    @Operation(summary = "search author by name or surname")
    public List<AuthorDto> getAuthorByNameOrSurname(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) String surname) {
        return authorService.returnAuthorByNameOrSurname(name, surname);
    }

    @GetMapping("/findAuthorByRandomWord")
    @Operation(summary = "search author by random word")
    public ResponseEntity<List<AuthorDto>> getAuthorByRandomWord(@RequestParam String randomWord) {
        List<AuthorDto> authors = authorService.getAuthorByRandomWord(randomWord);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
