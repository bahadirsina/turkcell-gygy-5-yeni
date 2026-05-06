package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.turkcell.spring_starter.dto.CreateProductRequest;
import com.turkcell.spring_starter.dto.CreatedProductResponse;
import com.turkcell.spring_starter.dto.ListProductResponse;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.entity.Product;
import com.turkcell.spring_starter.entity.Tag;
import com.turkcell.spring_starter.repository.CategoryRepository;
import com.turkcell.spring_starter.repository.ProductRepository;
import com.turkcell.spring_starter.repository.TagRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryServiceImpl;

    public ProductServiceImpl(ProductRepository productRepository, CategoryServiceImpl categoryServiceImpl) {
        this.productRepository = productRepository;
        this.categoryServiceImpl = categoryServiceImpl;
    }



    public void create(@RequestBody CreateProductRequest createProductRequest)
    {
        // 1. -> Eklenmek istenen ürünün kategorisi var olmalıdır.
        Category category = categoryServiceImpl.getById(createProductRequest.categoryId());

        if(category == null)
            throw new RuntimeException("Böyle bir kategori bulunamadı.");

        Product product = new Product();
        product.setName(createProductRequest.name());
        product.setDescription(createProductRequest.description());
        product.setCategory(category);

        productRepository.save(product);
    }
}
