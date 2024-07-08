package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAuthorByNameAndSurname(String name, String surname);
    List<Author> findAuthorByName(String name);
    List<Author> findAuthorBySurname(String surname);
    @Query(value = "SELECT * FROM author a WHERE a.name LIKE ?1 OR a.surname LIKE ?1 OR a.author_info LIKE ?1 ", nativeQuery = true)
    List<Author> findAuthorByRandomWord(String word);
}
