package com.turkcell.spring_starter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.turkcell.spring_starter.model.Product;
import com.turkcell.spring_starter.dto.ProductCreatedResponse;
import com.turkcell.spring_starter.dto.ProductForCreateDto;


@RestController //Anotasyon => Sınıfın bir RESTful web hizmeti denetleyicisi olduğunu belirtir.
@RequestMapping("/api/product") // localhost:8080/api/product
public class ProductController {
    // In Memory Calıs.çç
    private List<Product> productList = new ArrayList<>();

    @GetMapping() // localhost:8080/api/product/1
    public List<Product> getAllProducts() {
        return productList;
    }
    @GetMapping("/{id}") 
    public Product getProductById(@PathVariable int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null); // Listeden id == product.getId() olan ürünü bulur, yoksa null döner
    }
    @PostMapping // localhost:8080/api/product/
    public ProductCreatedResponse createProduct(@RequestBody ProductForCreateDto productDto) {

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setId(new Random().nextInt(999)); // Rastgele ID ataması (gerçek uygulamalarda veritabanı tarafından atanır)

        productList.add(product);

        ProductCreatedResponse response = new ProductCreatedResponse();
        response.setId(product.getId());    
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return response;

        // Ben Controller olarak iş kodu çalıştıramam ama bunu yapmam gerekli..
        // İş kodunu çalıştıracak olan yapıya BAĞIMLIYIM
        // Bağımlılık Enjeksiyonu -> Dependency Injection
    }
    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        
        Product productToUpdate = productList.stream()
                .filter(p -> p.getId() == product.getId())
                .findFirst()
                .orElseThrow(null);

        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) { 
        ///...
    }

    
}
