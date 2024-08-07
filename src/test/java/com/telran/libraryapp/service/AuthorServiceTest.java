package com.telran.libraryapp.service;

import com.telran.libraryapp.dto.AuthorDto;
import com.telran.libraryapp.entity.Author;
import com.telran.libraryapp.mapper.AuthorMapper;
import com.telran.libraryapp.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class AuthorServiceTest {
    private static AuthorService authorService;
    private static AuthorRepository authorRepository;
    private static AuthorMapper authorMapper;

    @BeforeEach
    public void init() {
        authorRepository = Mockito.mock(AuthorRepository.class);
        authorService = new AuthorService(authorRepository,authorMapper);
        authorMapper = Mappers.getMapper(AuthorMapper.class);
    }

    @Test
    public void getAll() {
        authorService.getAll();
        Mockito.verify(authorRepository).findAll();
    }

    @Test
    public void getNameAuthorByID() {
        Author author = new Author();
        author.setId(2L);
        Mockito.when(authorRepository.findById(2L)).thenReturn(Optional.of(author));
        AuthorDto authorDto = authorService.getNameAuthorByID(2L).get();
        Mockito.verify(authorRepository).findById(2L);
        assertEquals(author.getId(), authorDto.getId());

    }

    @Test
    public void add() {
        Author author = new Author();
        author.setName("Olga");

        Mockito.when(authorRepository.save(author)).thenReturn(author);
        authorService.add(authorMapper.entityToDto(author));

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

        boolean result = authorService.updateAuthor(authorMapper.entityToDto(author));

        Mockito.verify(authorRepository).findById(author.getId());
        Mockito.verify(authorRepository).save(author);
        assertTrue(result);
    }

    @Test
    public void removeAuthor() {
        Long id = 1l;
        authorService.removeAuthor(id);
        Mockito.verify(authorRepository).deleteById(id);
    }

    @Test
    public void returnAuthorByNameOrSurname() {
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
    public void getAuthorByRandomWord() {
        authorService.getAuthorByRandomWord("Jane");
        Mockito.verify(authorRepository).findAuthorByRandomWord("%Jane%");
    }
}
