package com.telran.libraryapp.service;
import com.telran.libraryapp.entity.Category;
import com.telran.libraryapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {


    private final CategoryRepository repository;
    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.getAll();
    }

    public List<Category> getByName(String name) {
        return repository.getAll().stream()
                .filter(category -> category.getName().startsWith(name))
                .toList();
    }

    public boolean updateCategory(Category category) {
        List<Category> categories = repository.getAll();

        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == category.getId()) {
                categories.set(i, category);
                return true;
            }
        }
        return false;

    }
    public void deleteCategory(int id) {
        repository.getAll().removeIf(category1 -> category1.getId()==id);
    }


    public Category createCategory(Category category) {
        List<Category> categories = repository.getAll();
        int newId = categories.stream().mapToInt(Category::getId).max().orElse(0) + 1;
        category.setId(newId);
        categories.add(category);
        return category;
    }
}
