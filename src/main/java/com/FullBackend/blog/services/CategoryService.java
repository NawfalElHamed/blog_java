package com.FullBackend.blog.services;

import com.FullBackend.blog.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();
}
