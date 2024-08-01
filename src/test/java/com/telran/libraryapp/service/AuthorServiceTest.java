package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Author;
import com.telran.libraryapp.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {
    private static AuthorService authorService;
    private static AuthorRepository authorRepository;

    @BeforeEach
    public void init() {
        authorRepository = Mockito.mock(AuthorRepository.class);
        authorService = new AuthorService(authorRepository);
    }

    @Test
    void getAll() {
        authorService.getAll();
        Mockito.verify(authorRepository).findAll();
    }

    @Test
    void getNameAuthorByID() {
        Long id = 2L;
        authorService.getNameAuthorByID(id);
        Mockito.verify(authorRepository).findById(id);
    }

    @Test
    public void add() {
        Author author = new Author();
        author.setId(156L);
        author.setName("Agata");
        authorService.add(author);
        Mockito.verify(authorRepository).save(author);
    }

    @Test
    public void updateAuthor() {
        Author author = new Author();
        author.setName("New name author");
        author.setId(123L);

        Author oldAuthor = new Author();
        oldAuthor.setName("Old name author");
        oldAuthor.setId(123L);

        Mockito.when(authorRepository.findById(author.getId())).thenReturn(Optional.of(oldAuthor));
        Mockito.when(authorRepository.save(author)).thenReturn(author);

        boolean result = authorService.updateAuthor(author);

        Mockito.verify(authorRepository).findById(author.getId());
        Mockito.verify(authorRepository).save(author);
        assertTrue(result);

        author = new Author();
        author.setName("New name author");
        author.setId(777L);

        Mockito.when(authorRepository.findById(777L)).thenReturn(Optional.empty());
        result = authorService.updateAuthor(author);
        assertFalse(result);

    }

    @Test
    public void removeAuthor() {
        Long id = 1l;
        authorService.removeAuthor(id);
        Mockito.verify(authorRepository).deleteById(id);
    }

    @Test
    void returnAuthorByNameOrSurname() {
        authorService.returnAuthorByNameOrSurname("Dave", "Big");
        Mockito.verify(authorRepository).findAuthorByNameAndSurname("Dave","Big");

        authorService.returnAuthorByNameOrSurname("Daniel", null);
        Mockito.verify(authorRepository).findAuthorByName("Daniel");

        authorService.returnAuthorByNameOrSurname(null, "Rowling");
        Mockito.verify(authorRepository).findAuthorBySurname("Rowling");

        authorService.returnAuthorByNameOrSurname(null, null);
        Mockito.verify(authorRepository).findAll();

    }

    @Test
    void getAuthorByRandomWord() {
        authorService.getAuthorByRandomWord("Jane");
        Mockito.verify(authorRepository).findAuthorByRandomWord("%Jane%");
    }
}