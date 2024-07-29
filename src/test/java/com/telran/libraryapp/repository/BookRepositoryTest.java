package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findBooksByCategoryId() {
        List<Book> booksByCategoryId = repository.findBooksByCategoryId(1L);
        assertEquals(3, booksByCategoryId.size());
    }

    @Test
    public void updateAmountOfBooksOptimized() {
        Book oldBookFromRepo = repository.findById(1L).get();
        assertEquals(2, oldBookFromRepo.getAvailableAmount());

        repository.updateAmountOfBooksOptimized(1L, 100);
        testEntityManager.clear();

        Book book = repository.findById(1L).get();
        assertEquals(100, book.getAvailableAmount());
    }

    @Test
    public void testFindById() {
        Book book = repository.findById(1L).get();
        assertEquals(2, book.getAvailableAmount());
    }
}