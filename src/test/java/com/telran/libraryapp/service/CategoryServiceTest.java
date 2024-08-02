package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Category;
import com.telran.libraryapp.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class CategoryServiceTest {
    private static CategoryService categoryService;
    private static CategoryRepository repository;

    @BeforeAll
    public static void setUp() {
        repository = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryService(repository);
    }

    @Test
    void getAll() {
        categoryService.getAll();
        Mockito.verify(repository).findAll();
    }

    @Test
    void getByName() {
        String name = "test";
        categoryService.getByName(name);
        Mockito.verify(repository).findCategoriesByName(name);
    }

    @Test
    void updateCategory() {
        Category category = new Category();
        category.setName("test");
        category.setId(2L);

        Mockito.when(repository.findById(category.getId())).thenReturn(Optional.of(category));
        Mockito.when(repository.save(category)).thenReturn(category);

        categoryService.updateCategory(category);
        Mockito.verify(repository).findById(category.getId());
        Mockito.verify(repository).save(category);

        Category category1 = new Category();
        category1.setName("new test");
        category1.setId(24L);

        Mockito.when(repository.findById(24L)).thenReturn(Optional.empty());
        Category result = categoryService.updateCategory(category1);
        Mockito.verify(repository,times(0)).save(category1);
        assertNull(result);


    }

    @Test
    void deleteCategory() {
        categoryService.deleteCategory(2L);
        Mockito.verify(repository).deleteById(2L);
    }

    @Test
    void add() {
        Category category = new Category();
        categoryService.add(category);
        Mockito.verify(repository).save(category);


    }
}