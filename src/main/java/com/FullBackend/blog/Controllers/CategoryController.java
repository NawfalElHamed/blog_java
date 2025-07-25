package com.FullBackend.blog.Controllers;


import com.FullBackend.blog.domain.dtos.CategoryDto;
import com.FullBackend.blog.domain.dtos.CreateCategoryRequest;
import com.FullBackend.blog.domain.entities.Category;
import com.FullBackend.blog.mappers.CategoryMapper;
import com.FullBackend.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor

public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = categoryService.listCategories().stream().map(
                categoryMapper::toDto
        ).toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping

    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {


        Category categoryToCreate = categoryMapper.toEntity(createCategoryRequest);

        Category savedCategory = categoryService.createCategory(categoryToCreate);
        return new ResponseEntity<>(categoryMapper.toDto(savedCategory), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
