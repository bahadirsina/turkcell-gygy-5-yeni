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
import com.turkcell.spring_starter.repository.ProductRepository;
import com.turkcell.spring_starter.service.ProductServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductServiceImpl productServiceImpl;

    public ProductsController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }



    @PostMapping
    public void create(@RequestBody @Valid CreateProductRequest createProductRequest)
    {
        this.productServiceImpl.create(createProductRequest);
    }
    
}
