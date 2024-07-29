package com.telran.libraryapp.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "{validation.category.name}")
    @Length(max = 45, message = "{validation.category.name}")
    private String name; // unique in DB
    @NotNull(message = "Category description should be not null and no more than 255 characters")
    @Length(max = 255, message = "{validation.category.description}")
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference("category")
    private List<Book> books;

}
