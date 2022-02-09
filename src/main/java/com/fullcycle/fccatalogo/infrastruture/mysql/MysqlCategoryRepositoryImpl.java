package com.fullcycle.fccatalogo.infrastruture.mysql;

import java.util.Optional;
import java.util.UUID;

import com.fullcycle.fccatalogo.domain.entity.Category;
import com.fullcycle.fccatalogo.domain.repository.ICategoryRepository;

import org.springframework.stereotype.Repository;

@Repository
public class MysqlCategoryRepositoryImpl implements ICategoryRepository {

    SpringDataCategoryRepository repository;

    public MysqlCategoryRepositoryImpl(SpringDataCategoryRepository repository) {
         this.repository = repository;
    }

    @Override
    public Iterable<Category> findAllCategories() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> findById(UUID idUUId) {
        return repository.findByIdUUID(idUUId);
    }

    @Override
    public Category createCategory(Category category) {
        return repository.save(category);
    }
    
}
