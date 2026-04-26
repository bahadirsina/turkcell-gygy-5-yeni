package com.turkcell.spring_starter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.dto.CreateProductRequest;
import com.turkcell.spring_starter.dto.CreatedProductResponse;
import com.turkcell.spring_starter.dto.ListProductResponse;
import com.turkcell.spring_starter.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductServiceImpl productServiceImpl;

    public ProductsController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping()
    public CreatedProductResponse create(@RequestBody CreateProductRequest createProductRequest) {
        return this.productServiceImpl.create(createProductRequest);
    }

    @GetMapping
    public List<ListProductResponse> getAll() {
        return this.productServiceImpl.getAll();
    }

    @GetMapping("{id}")
    public CreatedProductResponse getById(@PathVariable String id) {
        return this.productServiceImpl.getById(id);
    }

    @PutMapping("{id}")
    public CreatedProductResponse update(@PathVariable String id, @RequestBody CreateProductRequest entity) {
        return this.productServiceImpl.update(id, entity);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        this.productServiceImpl.delete(id);
    }
}
