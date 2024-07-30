package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    private static BookService bookService;
    private static BookRepository repository;

    @BeforeEach
    public void init() {
        repository = Mockito.mock(BookRepository.class);
        bookService = new BookService(repository);
    }



    @Test
    public void getAll() {
        bookService.getAll();
        Mockito.verify(repository).findAll();
    }

    @Test
    public void getAllByTitle() {
        String title = "TestData";
        Integer amount = 5;
        bookService.getAllByTitle(title, amount);
        Mockito.verify(repository).findBooksByTitleStartingWithAndAvailableAmountIsGreaterThanEqual(title, amount);

        bookService.getAllByTitle(title, null);
        Mockito.verify(repository).findBooksByTitleStartingWithAndAvailableAmountIsGreaterThanEqual(title, 0);
    }

    @Test
    public void addOrUpdate() {
        Book book = new Book();
        book.setTitle("Test title");

        Mockito.when(repository.save(book)).thenReturn(book);
        Book resultBook = bookService.addOrUpdate(book);

        Mockito.verify(repository).save(book);
        assertEquals(book.getTitle(), resultBook.getTitle());
    }

    @Test
    public void updateBook() {
        Book newBook = new Book();
        newBook.setTitle("New test title");
        newBook.setId(123L);

        Book oldBook = new Book();
        oldBook.setTitle("Old test title");
        oldBook.setId(123L);

        Mockito.when(repository.findById(newBook.getId())).thenReturn(Optional.of(oldBook));
        Mockito.when(repository.save(newBook)).thenReturn(newBook);

        Book result = bookService.updateBook(newBook);

        Mockito.verify(repository).findById(newBook.getId());
        Mockito.verify(repository).save(newBook);
        assertEquals(newBook.getTitle(), result.getTitle());

        newBook = new Book();
        newBook.setTitle("New test title");
        newBook.setId(777L);

        Mockito.when(repository.findById(777L)).thenReturn(Optional.empty());
        result = bookService.updateBook(newBook);
        assertNull(result);
    }

    @Test
    public void updateAmountOfBooks() {
        Book book = new Book();
        book.setTitle("Old test title");
        book.setId(1L);
        book.setAvailableAmount(1);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(book));
        Mockito.when(repository.save(book)).thenReturn(book);

        assertEquals(1, book.getAvailableAmount());
        Book updatedBook = bookService.updateAmountOfBooks(1L, 100).get();

        Mockito.verify(repository).findById(1L);
//        Mockito.verify(repository).save(any());
//        Mockito.verify(repository).save(eq(book));
//        Mockito.verify(repository, Mockito.times(1)).save(book);
//        Mockito.verify(repository, Mockito.never()).save(book);

        assertEquals(100, updatedBook.getAvailableAmount());

        Mockito.when(repository.findById(777L)).thenReturn(Optional.empty());
        Optional<Book> optional = bookService.updateAmountOfBooks(777L, 100);
        assertTrue(optional.isEmpty());

        Mockito.verify(repository, Mockito.times(1)).save(book);
    }
}