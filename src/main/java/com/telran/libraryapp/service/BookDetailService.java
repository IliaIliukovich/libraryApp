package com.telran.libraryapp.service;

import com.telran.libraryapp.dto.BookDetailDto;
import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.BookDetail;
import com.telran.libraryapp.mapper.BookDetailMapper;
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
    private final BookDetailMapper bookDetailMapper;


    @Autowired
    public BookDetailService(BookDetailRepository bookDetailRepository, BookRepository bookRepository, BookDetailMapper bookDetailMapper) {
        this.bookDetailRepository = bookDetailRepository;
        this.bookRepository = bookRepository;
        this.bookDetailMapper = bookDetailMapper;
    }

    public List<BookDetailDto> getAll() {
        List<BookDetail> bookDetails = bookDetailRepository.findAll();
        logger.debug("BookDetails retrieved from DB: {}", () -> bookDetails.stream().map(BookDetail::getPublisher).toList());
        return bookDetailMapper.entityListToDto(bookDetails);
    }


    public Optional<BookDetailDto> getBDById(Long id) {
        Optional<BookDetail> bookDetail = bookDetailRepository.findById(id);
        logger.debug("BookDetail retrieved from DB: id =  {}", () -> bookDetail.orElse(null));
        return Optional.of(bookDetailMapper.entityToDto(bookDetail.get()));

    }

    @Transactional
    public BookDetailDto add(BookDetailDto bookDetailDto, Long bookID) {
        BookDetail bookDetail = bookDetailMapper.dtoToEntity(bookDetailDto);
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
            return bookDetailMapper.entityToDto(savedBookDetail);
        }
        throw new RuntimeException("Book with id " + bookID + " not found");
    }

    public boolean updateBookDetail(BookDetailDto bookDetailDto) {
        Optional<BookDetail> optional = bookDetailRepository.findById(bookDetailDto.getId());
        if (optional.isPresent()) {
            bookDetailRepository.save(bookDetailMapper.dtoToEntity(bookDetailDto));
            logger.debug("BookDetail updated from DB: id =  {}", bookDetailDto.getId());
            return true;
        } else {
            logger.error("BookDetail not found : id = {}", bookDetailDto.getId());
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
