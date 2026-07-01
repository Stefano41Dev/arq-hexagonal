package com.stefano.dev.application.service;

import com.stefano.dev.application.command.category.SaveCategoryCommand;
import com.stefano.dev.application.usecase.category.CategoryUseCase;
import com.stefano.dev.domain.model.Category;
import com.stefano.dev.domain.port.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements CategoryUseCase {
    // Todo - Nota: Primera forma de crear un useCase, forma sencilla proyectos pequeños
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category save(SaveCategoryCommand saveCategoryCommand) {
        categoryRepository.findByName(saveCategoryCommand.name()).orElseThrow();

        return categoryRepository.save(Category.builder()
                .name(saveCategoryCommand.name())
                .build());
    }
}
