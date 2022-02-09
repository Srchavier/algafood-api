package com.fullcycle.fccatalogo.application.service.impl;

import java.util.Optional;
import java.util.UUID;

import com.fullcycle.fccatalogo.application.dto.CategoryDTO;
import com.fullcycle.fccatalogo.application.service.ICategoryService;
import com.fullcycle.fccatalogo.domain.entity.Category;
import com.fullcycle.fccatalogo.domain.repository.ICategoryRepository;

import org.springframework.stereotype.Service;

@Service
public class ICategoryServiceImpl implements ICategoryService {

    private ICategoryRepository repository;

    public ICategoryServiceImpl(ICategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Category> findAll() {

        return repository.findAllCategories();
    }

    @Override
    public Optional<Category> findById(UUID idUUId) {
        return repository.findById(idUUId);
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = categoryDTO.toEntity();
        return repository.createCategory(category);
    }
    
}
