package com.telran.libraryapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telran.libraryapp.entity.enums.AccessLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("category")
    @JoinColumn(name = "category_id")
    private Category category;

    private int availableAmount;

    private String isbn;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_detail_id")
    private BookDetail bookDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    @JsonIgnore
    private Building building;

    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;

    @ManyToMany(mappedBy = "authorHasBooks")
    private List<Author> authors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return availableAmount == book.availableAmount && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn) && accessLevel == book.accessLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, availableAmount, isbn, accessLevel);
    }
}