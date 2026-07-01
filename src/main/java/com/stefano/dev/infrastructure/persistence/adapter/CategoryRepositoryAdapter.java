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
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category.getName());
        var saveEntity = categoryJPARepository.save(categoryEntity);
        return Category.builder()
                .id(saveEntity.getId())
                .name(saveEntity.getName())
                .build();
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Category> list() {
        return List.of();
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public Void delete(Integer id) {
        return null;
    }
}
