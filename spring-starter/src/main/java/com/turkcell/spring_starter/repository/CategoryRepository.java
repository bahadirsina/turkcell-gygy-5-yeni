package com.turkcell.spring_starter.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.turkcell.spring_starter.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    // 1. JPQL - JPA + SQL
    // 2. Saf SQL
    @Query("SELECT c FROM Category c WHERE c.name LIKE %:query%")
    Set<Category> search(String query);

    //3. Named Queries 

}
