package com.turkcell.spring_starter.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @UuidGenerator()
    @Column(name = "id")
    private UUID id;

    @Column(name = "name",nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "category") //mappedBy -> İlişkiyi yöneten tarafı belirtir.
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
