package com.turkcell.spring_cqrs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.turkcell.spring_cqrs.domain.Category;

import java.util.Optional;
import java.util.UUID;


public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByName(String name);

}
