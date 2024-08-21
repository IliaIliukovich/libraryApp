package com.telran.libraryapp.repository;

import com.telran.libraryapp.dto.AuthorDto;
import com.telran.libraryapp.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AuthorRepositoryTest {
    @Autowired
    AuthorRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findAuthorByNameAndSurname() {
        List<Author> authors = repository.findAuthorByNameAndSurname("John", "Green");
        assertNotNull(authors);
    }

    @Test
    void findAuthorByName() {
        List<Author> authorsByName = repository.findAuthorByName("Jane");
        assertNotNull(authorsByName);
    }

    @Test
    void findAuthorBySurname() {
        List<Author> authorsBySurname = repository.findAuthorBySurname("Rowling");
        assertNotNull(authorsBySurname);
    }

    @Test
    void findAuthorByRandomWord() {
        List<Author> authorsByRandomWord = repository.findAuthorByRandomWord("John");
        assertEquals(authorsByRandomWord, repository.findAuthorByName("John"));
        testEntityManager.clear();

        authorsByRandomWord = repository.findAuthorByRandomWord("Rowling");
        assertEquals(authorsByRandomWord, repository.findAuthorBySurname("Rowling"));
        testEntityManager.clear();

        authorsByRandomWord = repository.findAuthorByRandomWord("and");
        assertEquals(authorsByRandomWord,List.of());
    }
}