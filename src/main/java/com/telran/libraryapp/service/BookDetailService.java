package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.BookDetail;
import com.telran.libraryapp.repository.BookDetailRepository;
import com.telran.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailService {
    private final BookDetailRepository bookDetailRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookDetailService(BookDetailRepository bookDetailRepository, BookRepository bookRepository) {
        this.bookDetailRepository = bookDetailRepository;
        this.bookRepository = bookRepository;
    }

    public List<BookDetail> getAll() {
        return bookDetailRepository.findAll();
    }


    public Optional<BookDetail> getBDById(Integer id) {
        return bookDetailRepository.findById(id);

    }

    public BookDetail add(BookDetail bookDetail, Long bookID) {
     BookDetail savedBookDetail = bookDetailRepository.save(bookDetail);
     Book book = bookRepository.findById(bookID).orElseThrow(()->  new IllegalArgumentException("There is no such book"));
     book.setBookDetail(savedBookDetail);
     bookRepository.save(book);
     return savedBookDetail;
    }

    public boolean updateBookDetail(BookDetail bookDetail) {
        Optional<BookDetail> optional = bookDetailRepository.findById(bookDetail.getId());
        if (optional.isPresent()) {
            bookDetailRepository.save(bookDetail);
            return true;
        } else {
            bookDetailRepository.save(bookDetail);
            return false;
        }
    }

    public void remove(Integer id) {
        bookDetailRepository.deleteById(id);
    }
}
