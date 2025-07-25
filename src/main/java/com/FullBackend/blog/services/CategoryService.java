package com.FullBackend.blog.services;

import com.FullBackend.blog.domain.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> listCategories();

    Category createCategory(Category category);

    void deleteCategory(UUID id);
}
