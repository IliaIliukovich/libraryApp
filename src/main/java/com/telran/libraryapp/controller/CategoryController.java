package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Category;
import com.telran.libraryapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;


    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> getCategories() {
        return service.getAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> getByName(@PathVariable String name) {
        Optional<Category> byName = service.getByName(name);
        if (byName.isPresent()) {
            return new ResponseEntity<>(byName.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
       Category add = service.add(category);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        boolean isUpdated = service.updateCategory(category);

        if (isUpdated) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}







