package com.telran.libraryapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "book_has_tag",
            joinColumns = @JoinColumn(name = "tag_id") ,
            inverseJoinColumns = @JoinColumn(name = "book_isbn"))
    private List<Book> books;
}


