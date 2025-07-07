package com.FullBackend.blog.services.impl;

import com.FullBackend.blog.domain.entities.Category;
import com.FullBackend.blog.repositories.CategoryRepository;
import com.FullBackend.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
       return categoryRepository.findAllWithPostCount();
    }
}
