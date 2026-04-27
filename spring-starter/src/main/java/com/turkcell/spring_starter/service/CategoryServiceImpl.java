package com.turkcell.spring_starter.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.turkcell.spring_starter.repository.CategoryRepository;
import com.turkcell.spring_starter.dto.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.ListCategoryResponse;
import com.turkcell.spring_starter.entity.Category;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Set;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;
    public CategoryServiceImpl(CategoryRepository categoryRepository, EntityManager entityManager) {
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    public CreatedCategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        //Veritabanında insert update çalıştırır.
        //entity id'e sahipse update
        //entity id'si null ise insert
        Category category = new Category();
        category.setName(createCategoryRequest.getName());

        category = this.categoryRepository.save(category); // ekledikten sonraki halini geri döner. id'si de vardır.

        CreatedCategoryResponse response = new CreatedCategoryResponse(); 
        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    }

    public List<ListCategoryResponse> getAll() {
        //Veritabanından select çalıştırır.
        //Tüm kategorileri getirir.
        //List<Category> -> List<ListCategoryResponse>
        List<Category> categories = this.categoryRepository.findAll();
        List<ListCategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            ListCategoryResponse response = new ListCategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            responseList.add(response);
        }
        return responseList;
    }

    public CreatedCategoryResponse update(String id, CreateCategoryRequest entity) {
        //Veritabanında update çalıştırır.
        //id'ye sahip kategoriyi günceller.
        //Güncellenmiş kategoriyi geri döner.
        UUID categoryId = UUID.fromString(id);
        Category existingCategory = this.categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        existingCategory.setName(entity.getName());
        Category updatedCategory = this.categoryRepository.save(existingCategory);
        
        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(updatedCategory.getId());
        response.setName(updatedCategory.getName());
        
        return response;
    }

    public CreatedCategoryResponse delete(String id) {
        //Veritabanında delete çalıştırır.
        //id'ye sahip kategoriyi siler.
        //Silinmiş kategoriyi geri döner.
        UUID categoryId = UUID.fromString(id);
        Category category = this.categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        
        this.categoryRepository.delete(category);
        
        return response;
    }

    public CreatedCategoryResponse getById(String id) {
        //Veritabanında select çalıştırır.
        //id'ye sahip kategoriyi getirir.
        //Getirilen kategoriyi geri döner.
        UUID categoryId = UUID.fromString(id);
        Category category = this.categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        
        return response;
    }

    public List<ListCategoryResponse> search(String query)
    {
        //Set<Category> categories = categoryRepository.findByNameLike("%" + query + "%");

        // String Concatination -> KESİNLİKLE YASAK
        //String jpql = "Select c from Category c Where c.name LIKE '%" + query + "%'";

        String jpql = "Select c from Category c Where c.name like :query";

        List<Category> categories = entityManager.createQuery(jpql, Category.class)
        .setParameter("query", "%" + query + "%")
        .getResultList();

        List<ListCategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            ListCategoryResponse response = new ListCategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            responseList.add(response);
        }

        return responseList;
    }
}
