package com.stefano.dev.infrastructure.persistence.adapter;


import com.stefano.dev.domain.model.Category;
import com.stefano.dev.domain.port.CategoryRepository;
import com.stefano.dev.infrastructure.persistence.entity.CategoryEntity;
import com.stefano.dev.infrastructure.persistence.repository.CategoryJPARepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final CategoryJPARepository categoryJPARepository;

    public CategoryRepositoryAdapter(CategoryJPARepository categoryJPARepository){
        this.categoryJPARepository = categoryJPARepository;
    }
    @Override
    public Category save(Category category) {

        var saveEntity = categoryJPARepository.save(CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .isActive(category.getIsActive())
                .build());

        return Category.builder()
                .id(saveEntity.getId())
                .name(saveEntity.getName())
                .isActive(category.getIsActive())
                .build();
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryJPARepository.findByIdAndIsActiveTrue(id).map(entity -> Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build());
    }

    @Override
    public List<Category> findByNameQuery(String name) {
        var listCategoryEntity = categoryJPARepository.findByNameContainingAndIsActiveTrue(name);

        return listCategoryEntity.stream().map(entity -> Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build())
                .toList();
    }

    @Override
    public Optional<Category> findByName(String name) {

        return  categoryJPARepository.findByNameAndIsActiveTrue(name).map(entity -> Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isActive(entity.getIsActive())
                .build());
    }

    @Override
    public List<Category> list() {
        var listCategoryEntity = categoryJPARepository.findAllByIsActiveTrue();

        return listCategoryEntity.stream().map(entity -> Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build())
                .toList();
    }
}
