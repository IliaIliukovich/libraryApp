package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.BookDetail;
import com.telran.libraryapp.repository.BookDetailRepository;
import com.telran.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public Optional<BookDetail> getBDById(Long id) {
        return bookDetailRepository.findById(id);

    }

    @Transactional
    public BookDetail add(BookDetail bookDetail, Long bookID) {
        Optional<Book> optional = bookRepository.findById(bookID);
        if (optional.isPresent()) {
            BookDetail savedBookDetail = bookDetailRepository.save(bookDetail);
            Book book = optional.get();
            BookDetail old = book.getBookDetail();
            book.setBookDetail(savedBookDetail);
            bookRepository.save(book);
            if (old != null) {
                bookDetailRepository.deleteById(old.getId());
            }
            return savedBookDetail;
        }
        throw new RuntimeException("Book with id " + bookID + " not found");
    }

    public boolean updateBookDetail(BookDetail bookDetail) {
        Optional<BookDetail> optional = bookDetailRepository.findById(bookDetail.getId());
        if (optional.isPresent()) {
            bookDetailRepository.save(bookDetail);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void remove(Long id) {
        BookDetail bookDetail = bookDetailRepository.getReferenceById(id);
        Optional<Book> optional = bookRepository.findBookByBookDetail(bookDetail);
        if (optional.isPresent()) {
            Book book = optional.get();
            book.setBookDetail(null);
            bookRepository.save(book);
        }
        bookDetailRepository.deleteById(id);
    }
}
