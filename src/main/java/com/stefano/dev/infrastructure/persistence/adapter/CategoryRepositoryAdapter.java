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
                .name(category.getName())
                .build());

        return Category.builder()
                .id(saveEntity.getId())
                .name(saveEntity.getName())
                .build();
    }

    @Override
    public Optional<Category> findById(Integer id) {
        var categoryEntity = categoryJPARepository.findById(id).orElseThrow();

        return Optional.ofNullable(Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build());
    }

    @Override
    public List<Category> findByName(String name) {
        var listCategoryEntity = categoryJPARepository.findByNameContaining(name);

        return listCategoryEntity.stream().map(entity -> Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build())
                .toList();
    }

    @Override
    public List<Category> list() {
        var listCategoryEntity = categoryJPARepository.findAll();

        return listCategoryEntity.stream().map(entity -> Category.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build())
                .toList();
    }

    @Override
    public Category update(Integer id, Category category) {
        var categoryEntity = categoryJPARepository.findById(id).orElseThrow();

        CategoryEntity updateEntity = categoryJPARepository.save(CategoryEntity.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build());

        return Category.builder()
                .id(updateEntity.getId())
                .name(updateEntity.getName())
                .build();
    }

    @Override
    public void delete(Integer id) {
        var category = categoryJPARepository.findById(id).orElseThrow();
        categoryJPARepository.delete(category);
    }
}
