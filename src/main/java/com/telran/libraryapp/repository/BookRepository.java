package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findBooksByCategory(String category);

    List<Book> findBooksByTitleStartingWithAndAvailableAmountIsGreaterThanEqual(String title, Integer amount);

    @Query("select b from Book b where b.title like ?1% and b.availableAmount >= ?2")
    List<Book> customQuery(String title, Integer amount);

    @Query(value = "select * from book b where b.title like ?1% and b.available_amount >= ?2", nativeQuery = true)
    List<Book> customNativeQuery(String title, Integer amount);

}
