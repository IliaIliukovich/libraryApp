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

    @NotNull(message = "{validation.book.title}")
    @Length(max = 90, message = "{validation.book.title}")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("category")
    private Category category;

    @Min(value = 0, message = "{validation.book.availableAmount}")
    private int availableAmount;

    @Pattern(regexp = "[\\d]{13}", message = "{validation.book.isbn}")
    private String isbn;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private BookDetail bookDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("building")
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