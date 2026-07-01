package com.stefano.dev.infrastructure.persistence.repository;

import com.stefano.dev.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJPARepository extends JpaRepository<CategoryEntity, Integer> {
}
