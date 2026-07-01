package com.stefano.dev.application.service;

import com.stefano.dev.application.command.category.SaveCategoryCommand;
import com.stefano.dev.application.usecase.category.CategoryUseCase;
import com.stefano.dev.domain.model.Category;
import com.stefano.dev.domain.port.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryUseCase {
    // Todo - Nota: Primera forma de crear un useCase, forma sencilla proyectos pequeños
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(SaveCategoryCommand saveCategoryCommand) {

        return categoryRepository.save(Category.builder()
                .name(saveCategoryCommand.name())
                .build());
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> list() {
        return categoryRepository.list();
    }

    @Override
    public Category update(Integer id, SaveCategoryCommand saveCategoryCommand) {
        return categoryRepository.update(id, Category.builder().name(saveCategoryCommand.name()).build());
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.delete(id);
    }

}
