package com.telran.libraryapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BookDetail {
    @Id
    private Integer id;
    private String publisher;
    private Integer year;
    private String abstractToBook;


}





