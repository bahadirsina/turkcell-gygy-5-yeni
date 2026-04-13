package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.ProductCreatedResponse;
import com.turkcell.spring_starter.dto.ProductForCreateDto;
import com.turkcell.spring_starter.model.Product;

//Implentation
//IProductService ❌
//ProductService ✅
//ProductServiceImpl ✅
@Service
public class ProductServiceImpl {

//Controller'ın size aktaracağı işleri tamamla.
//İş kodu..

//repo
    private final List<Product> productsInMemory = new ArrayList<>();

    public ProductCreatedResponse create(ProductForCreateDto productDto)
    {
        Product productWithSameName = productsInMemory.stream()
                .filter(p -> p.getName().equalsIgnoreCase(productDto.getName()))
                .findFirst()
                .orElse(null);

        if (productWithSameName != null) {
            throw new RuntimeException("Bu isimde bir ürün zaten mevcut.");
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setId(new Random().nextInt(999));

        productsInMemory.add(product); // repo

        ProductCreatedResponse response = new ProductCreatedResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return response;
    }

    public void update() {
        //Aynı iş kuralı

        checkIfProductWithNameExists("");
    }

    private void checkIfProductWithNameExists(String name) {
        Product productWithSameName = productsInMemory.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (productWithSameName != null) {
            throw new RuntimeException("Aynı isimde 2 ürün eklenemez.");
        }
    }
}



//Auto-Generated

//IProductRepository -> ProductRepository

//IProductRepository <Product> -> Spring auto-generated

//Spring IoC Nedir? Bean,Service nedir? Bunu araştır. Diğer dersin konusu.
