package com.telran.libraryapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String title;

    private String author;

    private String category;

    private int availableAmount;

    private String isbn;

}
