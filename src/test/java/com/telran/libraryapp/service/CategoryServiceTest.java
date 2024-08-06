package com.telran.libraryapp.service;

import com.telran.libraryapp.dto.CategoryDto;
import com.telran.libraryapp.entity.Category;
import com.telran.libraryapp.mapper.CategoryMapper;
import com.telran.libraryapp.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

class CategoryServiceTest {
    private static CategoryService categoryService;
    private static CategoryRepository repository;
    private static CategoryMapper categoryMapper;

    @BeforeAll
    public static void setUp() {
        repository = Mockito.mock(CategoryRepository.class);
        categoryMapper = Mappers.getMapper(CategoryMapper.class);
        categoryService = new CategoryService(repository, categoryMapper);
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

        categoryService.updateCategory(categoryMapper.entityToDto(category));
        Mockito.verify(repository).findById(category.getId());
        Mockito.verify(repository).save(category);

        Category category1 = new Category();
        category1.setName("new test");
        category1.setId(24L);

        Mockito.when(repository.findById(24L)).thenReturn(Optional.empty());
        CategoryDto result = categoryService.updateCategory(categoryMapper.entityToDto(category1));
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
        categoryService.add(categoryMapper.entityToDto(category));
        Mockito.verify(repository).save(eq(category));


    }
}