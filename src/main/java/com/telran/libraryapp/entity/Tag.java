package com.telran.libraryapp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private int tagId;
    private String name;
    private String description;

    private static List<Tag> tagList = new ArrayList<>(Arrays.asList(
            new Tag(1, "New", "description new Books"),
            new Tag(2, "Java", "description Java"),
            new Tag(3, "Fantasy", "description Fantasy"),
            new Tag(4, "Math", "description Math")
    ));

}

