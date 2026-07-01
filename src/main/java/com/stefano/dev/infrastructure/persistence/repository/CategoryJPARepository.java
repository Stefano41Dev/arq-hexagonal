package com.stefano.dev.infrastructure.persistence.repository;

import com.stefano.dev.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CategoryJPARepository extends JpaRepository<CategoryEntity, Integer> {
    List<CategoryEntity> findByNameContainingAndIsActiveTrue(String name);
    Optional<CategoryEntity> findByIdAndIsActiveTrue(Integer id);
    Optional<CategoryEntity> findByNameAndIsActiveTrue(String name);
}
