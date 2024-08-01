package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.BookDetail;
import com.telran.libraryapp.repository.BookDetailRepository;
import com.telran.libraryapp.repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailService {

    private static final Logger logger = LogManager.getLogger(BookDetailService.class);

    private final BookDetailRepository bookDetailRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookDetailService(BookDetailRepository bookDetailRepository, BookRepository bookRepository) {
        this.bookDetailRepository = bookDetailRepository;
        this.bookRepository = bookRepository;
    }

    public List<BookDetail> getAll() {
        List<BookDetail> bookDetails = bookDetailRepository.findAll();
        logger.debug("BookDetails retrieved from DB: {}", () -> bookDetails.stream().map(BookDetail::getPublisher).toList());
        return bookDetails;
    }


    public Optional<BookDetail> getBDById(Long id) {
        Optional<BookDetail> bookDetail = bookDetailRepository.findById(id);
        logger.debug("BookDetail retrieved from DB: id =  {}", () -> bookDetail.orElse(null));
        return bookDetail;

    }

    @Transactional
    public BookDetail add(BookDetail bookDetail, Long bookID) {
        Optional<Book> optional = bookRepository.findById(bookID);
        if (optional.isPresent()) {
            logger.debug("Book exists in DB: {}", bookID);
            BookDetail savedBookDetail = bookDetailRepository.save(bookDetail);
            Book book = optional.get();
            BookDetail old = book.getBookDetail();
            book.setBookDetail(savedBookDetail);
            bookRepository.save(book);
            logger.debug("BookDetail added to Book from DB: {}", () -> savedBookDetail);
            if (old != null) {
                bookDetailRepository.deleteById(old.getId());
                logger.debug("BookDetail deleted from DB: {}", () -> savedBookDetail);
            }
            return savedBookDetail;
        }
        throw new RuntimeException("Book with id " + bookID + " not found");
    }

    public boolean updateBookDetail(BookDetail bookDetail) {
        Optional<BookDetail> optional = bookDetailRepository.findById(bookDetail.getId());
        if (optional.isPresent()) {
            bookDetailRepository.save(bookDetail);
            logger.debug("BookDetail updated from DB: id =  {}", bookDetail.getId());
            return true;
        } else {
            logger.error("BookDetail not found : id = {}", bookDetail.getId());
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
            logger.info("BookDetail to the Book {} removed from DB", bookDetail);

        }
        bookDetailRepository.deleteById(id);
        logger.debug("BookDetail removed from DB: {}", () -> bookDetail);
    }
}
