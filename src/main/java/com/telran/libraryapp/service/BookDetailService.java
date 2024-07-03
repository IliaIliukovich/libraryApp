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
        return bookDetailRepository.getAll();

    }

    public Optional<BookDetail> getBDById(Integer id) {
        return bookDetailRepository.getAll().stream().filter(bd -> bd.getId() == id).findAny();
    }

    public void add(BookDetail bookDetail) {
        bookDetailRepository.getAll().add(bookDetail);
    }


    public boolean updateBookDetail(BookDetail bookDetail) {
        List<BookDetail> bookDetailList = bookDetailRepository.getAll();
        if (bookDetailList.contains(bookDetail)) {
            int index = bookDetailList.indexOf(bookDetail);
            bookDetailList.set(index, bookDetail);
            return true;
        } else {
            bookDetailList.add(bookDetail);
            return false;
        }
    }

    public void deleteById(Integer id) {
        bookDetailRepository.getAll().removeIf(bd -> bd.getId() == id);
    }
}
