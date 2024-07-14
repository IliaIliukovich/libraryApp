package com.telran.libraryapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    @NotNull(message = "{validation.tag.name}")
    @Length(max = 45, message = "{validation.tag.name}")
    private String name;

    private String description;

    @ManyToMany
    @JoinTable(name = "book_has_tag",
            joinColumns = @JoinColumn(name = "tag_id") ,
            inverseJoinColumns = @JoinColumn(name = "book_isbn"))
    private List<Book> books;
}


