package com.turkcell.spring_starter.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.turkcell.spring_starter.service.CategoryServiceImpl;
import com.turkcell.spring_starter.dto.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.ListCategoryResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



// Bu projedeki tüm entityler için tüm CRUD işlemleri kodlanmalı.
// GET-GET BY ID-ADD-UPDATE-DELETE

// Kütüphane sisteminizi code-first oluşturun. 

//JPQL 

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryServiceImpl categoryServiceImpl;

    public CategoriesController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }
    @PostMapping()
    public CreatedCategoryResponse create(@RequestBody CreateCategoryRequest createCategoryRequest) {

        return this.categoryServiceImpl.create(createCategoryRequest);
    }
    @GetMapping
    public List<ListCategoryResponse> getAll() {
        return this.categoryServiceImpl.getAll();
    }

    @PutMapping("{id}")
    public CreatedCategoryResponse update(@PathVariable String id, @RequestBody CreateCategoryRequest entity) {
        return this.categoryServiceImpl.update(id, entity);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        this.categoryServiceImpl.delete(id);
    }

    @GetMapping("{id}")
    public CreatedCategoryResponse getById(@PathVariable String id) {
        return this.categoryServiceImpl.getById(id);
    }
    @GetMapping("search")
    public List<ListCategoryResponse> search(@RequestParam String query) {
        return this.categoryServiceImpl.search(query);
    }
    
}
