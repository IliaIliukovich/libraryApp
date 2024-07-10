package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {

}
