package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.CreateProductRequest;
import com.turkcell.spring_starter.dto.CreatedProductResponse;
import com.turkcell.spring_starter.dto.ListProductResponse;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.entity.Product;
import com.turkcell.spring_starter.entity.Tag;
import com.turkcell.spring_starter.repository.CategoryRepository;
import com.turkcell.spring_starter.repository.ProductRepository;
import com.turkcell.spring_starter.repository.TagRepository;

@Service
public class ProductServiceImpl {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
            TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    public CreatedProductResponse create(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setCategory(resolveCategory(createProductRequest.getCategoryId()));
        product.setTags(resolveTags(createProductRequest.getTagIds()));

        product = this.productRepository.save(product);
        return toCreatedResponse(product);
    }

    public List<ListProductResponse> getAll() {
        List<Product> products = this.productRepository.findAll();
        List<ListProductResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            responseList.add(toListResponse(product));
        }

        return responseList;
    }

    public CreatedProductResponse getById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        try {
            UUID productId = UUID.fromString(id);
            Product product = this.productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));

            return toCreatedResponse(product);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product ID format: " + id);
        }
    }

    public CreatedProductResponse update(String id, CreateProductRequest entity) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        try {
            UUID productId = UUID.fromString(id);
            Product existingProduct = this.productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));

            existingProduct.setName(entity.getName());
            existingProduct.setDescription(entity.getDescription());
            existingProduct.setCategory(resolveCategory(entity.getCategoryId()));
            existingProduct.setTags(resolveTags(entity.getTagIds()));

            Product updatedProduct = this.productRepository.save(existingProduct);
            return toCreatedResponse(updatedProduct);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product ID format: " + id);
        }
    }

    public CreatedProductResponse delete(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        try {
            UUID productId = UUID.fromString(id);
            Product product = this.productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));

            CreatedProductResponse response = toCreatedResponse(product);
            this.productRepository.delete(product);
            return response;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product ID format: " + id);
        }
    }

    private Category resolveCategory(String categoryId) {
        if (categoryId == null || categoryId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product categoryId cannot be null or empty");
        }

        try {
            UUID categoryUuid = UUID.fromString(categoryId);
            return this.categoryRepository.findById(categoryUuid)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + categoryId));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid categoryId format: " + categoryId);
        }
    }

    private Set<Tag> resolveTags(List<String> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) {
            return new HashSet<>();
        }

        Set<Tag> tags = new HashSet<>();
        for (String tagId : tagIds) {
            if (tagId == null || tagId.trim().isEmpty()) {
                throw new IllegalArgumentException("Tag ID cannot be null or empty");
            }
            try {
                UUID tagUuid = UUID.fromString(tagId);
                Tag tag = this.tagRepository.findById(tagUuid)
                        .orElseThrow(() -> new IllegalArgumentException("Tag not found with id: " + tagId));
                tags.add(tag);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid tagId format: " + tagId);
            }
        }

        return tags;
    }

    private CreatedProductResponse toCreatedResponse(Product product) {
        CreatedProductResponse response = new CreatedProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryId(product.getCategory().getId());
        response.setTagIds(product.getTags() == null ? new ArrayList<>()
                : product.getTags().stream().map(Tag::getId).collect(Collectors.toList()));
        return response;
    }

    private ListProductResponse toListResponse(Product product) {
        ListProductResponse response = new ListProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryId(product.getCategory().getId());
        response.setTagIds(product.getTags() == null ? new ArrayList<>()
                : product.getTags().stream().map(Tag::getId).collect(Collectors.toList()));
        return response;
    }
}
