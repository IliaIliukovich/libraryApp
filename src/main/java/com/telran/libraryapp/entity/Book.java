package com.telran.libraryapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telran.libraryapp.entity.enums.AccessLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Category category;

    private int availableAmount;

    private String isbn;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private BookDetail bookDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Building building;

    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}