package com.fullcycle.fccatalogo.infrastruture.mysql;

import java.util.Optional;
import java.util.UUID;

import com.fullcycle.fccatalogo.domain.entity.Category;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SpringDataCategoryRepository extends PagingAndSortingRepository<Category, Integer>{

    @Query(value = "select * from categories c where c.idUUID = ?1", nativeQuery = true )
    Optional<Category> findByIdUUID(UUID idUUId);
    
}
