package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.BookDetail;
import com.telran.libraryapp.repository.BookDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailService {
    private final BookDetailRepository bookDetailRepository;

    @Autowired
    public BookDetailService(BookDetailRepository bookDetailRepository) {
        this.bookDetailRepository = bookDetailRepository;
    }

    public List<BookDetail> getAll() {
        return bookDetailRepository.findAll();
    }


    public Optional<BookDetail> getBDById(Integer id) {
        return bookDetailRepository.findById(id);

    }

    public void add(BookDetail bookDetail) {
        bookDetailRepository.save(bookDetail);

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
