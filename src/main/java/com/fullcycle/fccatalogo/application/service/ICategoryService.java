package com.fullcycle.fccatalogo.application.service;

import java.util.Optional;
import java.util.UUID;

import com.fullcycle.fccatalogo.application.dto.CategoryDTO;
import com.fullcycle.fccatalogo.domain.entity.Category;

public interface ICategoryService {
    Iterable<Category> findAll();
    Optional<Category> findById(UUID idUUId);
    Category createCategory(CategoryDTO category);

}
