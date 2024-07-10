package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByCategoryId(Integer category);

    List<Book> findBooksByTitleStartingWithAndAvailableAmountIsGreaterThanEqual(String title, Integer amount);

    @Query("select b from Book b where b.title like ?1% and b.availableAmount >= ?2")
    List<Book> customQuery(String title, Integer amount);

    @Query(value = "select * from book b where b.title like ?1% and b.available_amount >= ?2", nativeQuery = true)
    List<Book> customNativeQuery(String title, Integer amount);

    @Query(value = "update Book b set b.availableAmount = ?2 where b.id = ?1")
    @Modifying
    @Transactional
    void updateAmountOfBooksOptimized(Long id, Integer amount);

    Optional<Book> findBookByBookDetail(BookDetail bookDetail);

}
