package com.fullcycle.fccatalogo.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.fullcycle.fccatalogo.domain.entity.Category;

public interface ICategoryRepository {

    Iterable<Category> findAllCategories();

    Category createCategory(Category category);

    Optional<Category> findById(UUID idUUId);
    
}
