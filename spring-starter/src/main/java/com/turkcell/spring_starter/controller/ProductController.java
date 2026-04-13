package com.turkcell.spring_starter.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.dto.ProductCreatedResponse;
import com.turkcell.spring_starter.dto.ProductForCreateDto;
import com.turkcell.spring_starter.service.ProductServiceImpl;
import jakarta.validation.Valid;

// Altın kural: Veritabanı nesneleri requestte de responseda da kullanılamaz.
@RestController // Uygulamada gerektiğinde controlleri newle.
@RequestMapping("/api/product") 
public class ProductController {


    //private final ProductServiceImpl productServiceImpl = new ProductServiceImpl();
    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ProductCreatedResponse create(@RequestBody @Valid ProductForCreateDto productDto) {
        return this.productServiceImpl.create(productDto);
    }
}