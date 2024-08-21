package com.telran.libraryapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BookDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_detail_id", columnDefinition = "int")
    private Long id;

    private String publisher;

    private String year;
    @Column(name="abstract")
    private String abstractToBook;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDetail that = (BookDetail) o;
        return Objects.equals(id, that.id) && Objects.equals(publisher, that.publisher) && Objects.equals(year, that.year) && Objects.equals(abstractToBook, that.abstractToBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publisher, year, abstractToBook);
    }
}





