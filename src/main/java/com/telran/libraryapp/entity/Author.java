package com.telran.libraryapp.entity;

import jakarta.persistence.*;
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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max=45,message = "{validation.author.name}")
    @Pattern(regexp = "[A-Za-z\\s.'-]{1,45}", message = "{validation.author.name}")
    private String name;

    @Length(max=45,message = "{validation.author.surname}")
    @Pattern(regexp = "[A-Za-z\\s.'-]{0,45}", message = "{validation.author.surname}")
    private String surname;

    @Length(max=255)
    private String authorInfo;

    @ManyToMany
    @JoinTable(name = "book_has_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> authorHasBooks;


}