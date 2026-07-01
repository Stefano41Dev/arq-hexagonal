package com.stefano.dev.infrastructure.persistence.repository;

import com.stefano.dev.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryJPARepository extends JpaRepository<CategoryEntity, Integer> {
    List<CategoryEntity> findByNameContaining(String name);
}
