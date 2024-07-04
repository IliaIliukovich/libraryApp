package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    public List<Category> getAll() {
        return Category.categories;
    }
}
