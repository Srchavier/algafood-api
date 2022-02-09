package com.fullcycle.fccatalogo.application.web;

import java.util.Optional;
import java.util.UUID;

import com.fullcycle.fccatalogo.application.dto.CategoryDTO;
import com.fullcycle.fccatalogo.application.service.ICategoryService;
import com.fullcycle.fccatalogo.domain.entity.Category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private ICategoryService service;

    CategoryController(ICategoryService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Category> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> findAll(@PathVariable("id") UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody CategoryDTO category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCategory(category));
    }
    
}
