package com.FullBackend.blog.services.impl;

import com.FullBackend.blog.domain.entities.Category;
import com.FullBackend.blog.repositories.CategoryRepository;
import com.FullBackend.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {

        String name = category.getName();
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new IllegalArgumentException("Category  already exists with name " + name);
        }
        return categoryRepository.save(category);

    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            if (!category.get().getPosts().isEmpty()) {
                throw new IllegalArgumentException("Cannot delete category with posts");
            }
            categoryRepository.deleteById(id);
        }
    }


}
