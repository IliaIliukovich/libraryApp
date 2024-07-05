package com.telran.libraryapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    private int id;
    private String name;
    private String description;
    public static List<Category> categories =  new ArrayList<>(Arrays.asList(
            new Category(1,"Java","Learning Java"),
            new Category(2,"Dystopian","Learning Dystopian"),
            new Category(3,"Fantasy","Art Fantasy"),
            new Category(4,"Epic","Art Epic"),
            new Category(5,"Adventure","Art Adventure"),
            new Category(6,"Modernist","Art Modernist"),
            new Category(7,"Magic Realism","Art Magic Realism"),
            new Category(8,"Fiction","Art Fiction"),
            new Category(9,"Romance","Hot Romance")

    ));

}
