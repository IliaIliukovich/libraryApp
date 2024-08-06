package com.telran.libraryapp.service;

import com.telran.libraryapp.dto.CategoryDto;
import com.telran.libraryapp.entity.Category;
import com.telran.libraryapp.mapper.CategoryMapper;
import com.telran.libraryapp.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    private static final Logger logger = LogManager.getLogger(CategoryService.class);


    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository repository,CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAll() {
        List<Category> categories = repository.findAll();
        logger.debug("Categories retrieved from DB: {}", ()->categories.stream().map(Category::getName).toList());
        return categoryMapper.entityListToDto(categories);
    }

    public Optional<CategoryDto> getByName(String name) {
        Optional<Category> optional = repository.findCategoriesByName(name);
        return optional.map(category -> categoryMapper.entityToDto(category));
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Long id = categoryDto.getId();
        Optional<Category> optional = repository.findById(id);
        if (optional.isPresent()) {
            Category category = categoryMapper.dtoToEntity(categoryDto);
            Category savedCategory = repository.save(category);
            return categoryMapper.entityToDto(savedCategory);
        } else {
            return null;
        }
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    public CategoryDto add(CategoryDto categoryDto) {
        Category category = categoryMapper.dtoToEntity(categoryDto);
        Category createCategory = repository.save(category);
        return categoryMapper.entityToDto(createCategory);
    }

}
