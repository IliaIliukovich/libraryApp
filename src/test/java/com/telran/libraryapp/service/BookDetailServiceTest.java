package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.BookDetail;
import com.telran.libraryapp.repository.BookDetailRepository;
import com.telran.libraryapp.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookDetailServiceTest {
    private static BookDetailService bookDetailService;
    private static BookDetailRepository bookDetailRepository;
    private static BookRepository bookRepository;


    @BeforeEach
    public void init() {
        bookDetailRepository = Mockito.mock(BookDetailRepository.class);
        bookRepository = Mockito.mock(BookRepository.class);
        bookDetailService = new BookDetailService(bookDetailRepository, bookRepository);
    }

    @Test
    public void getAll() {
        bookDetailService.getAll();
        Mockito.verify(bookDetailRepository).findAll();
    }

    @Test
    public void getBDById() {
        Long testId = 77L;
        when(bookDetailRepository.findById(testId)).thenReturn(Optional.of(new BookDetail(testId, "Test", "1987", "dsd")));
        Optional<BookDetail> result = bookDetailService.getBDById(testId);
        assertEquals("Test", result.get().getPublisher());
        Mockito.verify(bookDetailRepository).findById(testId);

    }

    @Test
    public void add() {
        BookDetail newBookDetail = new BookDetail();
        Book book = new Book();
        Long bookId = 88L;
        book.setId(bookId);
        newBookDetail.setId(33L);
        newBookDetail.setPublisher("Test publisher");

        BookDetail bookDetail = new BookDetail();
        bookDetail.setId(1L);
        bookDetail.setPublisher("Existed publisher");
        book.setBookDetail(bookDetail);

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        Mockito.when(bookDetailRepository.save(newBookDetail)).thenReturn(newBookDetail);
        Mockito.when(bookRepository.save(book)).thenReturn(book);

        BookDetail result = bookDetailService.add(newBookDetail, bookId);

        assertEquals(newBookDetail, result);
        Mockito.verify(bookRepository).findById(bookId);
        Mockito.verify(bookDetailRepository).save(newBookDetail);
        Mockito.verify(bookRepository).save(book);
        Mockito.verify(bookDetailRepository).deleteById(bookDetail.getId());
    }


    @Test
    public void addNotFound() {
        BookDetail bookDetail = new BookDetail();
        Long bookId = 88L;

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookDetailService.add(bookDetail, bookId);
        });
        assertEquals("Book with id 88 not found", exception.getMessage());
        Mockito.verify(bookRepository).findById(bookId);
        Mockito.verify(bookDetailRepository, never()).save(any(BookDetail.class));


    }

    @Test
    public void updateBookDetail() {
        BookDetail newBookDetail = new BookDetail();
        newBookDetail.setPublisher("New publisher");
        newBookDetail.setId(44L);

        BookDetail oldBookDetail = new BookDetail();
        oldBookDetail.setPublisher("Old publisher");
        oldBookDetail.setId(44L);

        Mockito.when(bookDetailRepository.findById(oldBookDetail.getId())).thenReturn(Optional.of(oldBookDetail));
        Mockito.when(bookDetailRepository.save(newBookDetail)).thenReturn(newBookDetail);

        Boolean result = bookDetailService.updateBookDetail(newBookDetail);

        Mockito.verify(bookDetailRepository).findById(newBookDetail.getId());
        Mockito.verify(bookDetailRepository).save(newBookDetail);
        assertTrue(result);

    }

    @Test
    public void updateBookDetailNotFound() {

        BookDetail newBookDetail = new BookDetail();
        newBookDetail.setPublisher("New publisher");
        newBookDetail.setId(44L);

        Mockito.when(bookDetailRepository.findById(newBookDetail.getId())).thenReturn(Optional.empty());
        Boolean result = bookDetailService.updateBookDetail(newBookDetail);


        Mockito.verify(bookDetailRepository).findById(newBookDetail.getId());
        Mockito.verify(bookDetailRepository, never()).save(any(BookDetail.class));
        assertEquals(false, result);
    }


    @Test
    public void remove() {
        Long id = 123L;
        BookDetail bookDetail = new BookDetail();
        Book book  = new Book();
        book.setBookDetail(bookDetail);

        Mockito.when(bookDetailRepository.getReferenceById(id)).thenReturn(bookDetail);
        Mockito.when(bookRepository.findBookByBookDetail(bookDetail)).thenReturn(Optional.of(book));

         bookDetailService.remove(id);

        Mockito.verify(bookDetailRepository).getReferenceById(id);
        Mockito.verify(bookRepository).findBookByBookDetail(bookDetail);
        Mockito.verify(bookRepository).save(book);
        Mockito.verify(bookDetailRepository).deleteById(id);

        assertNull(book.getBookDetail());


    }
}