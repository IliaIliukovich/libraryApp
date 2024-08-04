package com.telran.libraryapp.controller;

import com.telran.libraryapp.dto.CategoryDto;
import com.telran.libraryapp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Validated
@RestController
@RequestMapping("/categories")
@Tag(name = "Category Controller",description = "Actions with categories")
public class CategoryController {

    private final CategoryService service;


    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Retrieve all category")
    public List<CategoryDto> getCategories() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update categories")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto category) {
        CategoryDto updateCategory = service.updateCategory(category);
        return new ResponseEntity<>(updateCategory,(category!= null) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Create categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto category) {
       CategoryDto add = service.add(category);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete categories")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}







