package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.BookDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDetailRepository {
    public List<BookDetail> getAll() {
        return BookDetail.bookDetailsList;
    }
}
