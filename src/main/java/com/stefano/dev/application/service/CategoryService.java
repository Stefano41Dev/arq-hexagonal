package com.stefano.dev.application.service;

import com.stefano.dev.application.command.category.SaveCategoryCommand;
import com.stefano.dev.application.command.category.UpdateCategoryNameCommand;
import com.stefano.dev.application.usecase.category.CategoryUseCase;
import com.stefano.dev.domain.exception.ErrorNegocio;
import com.stefano.dev.domain.model.Category;
import com.stefano.dev.domain.port.CategoryRepository;
import org.springframework.http.HttpStatus;
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
        if(categoryRepository.findByName(saveCategoryCommand.name()).isPresent()){
            throw new ErrorNegocio("El nombre " + saveCategoryCommand.name() + " ya esta en uso",HttpStatus.CONFLICT);
        }

        return categoryRepository.save(Category.builder()
                .name(saveCategoryCommand.name())
                .build());
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(()-> new ErrorNegocio("No existe la categoria con id: "+id, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByNameQuery(name);
    }

    @Override
    public List<Category> list() {
        return categoryRepository.list();
    }

    @Override
    public Category update(Integer id, UpdateCategoryNameCommand updateCategoryNameCommand) {
        var category = categoryRepository.findById(id).orElseThrow(()-> new ErrorNegocio("No existe la categoria con id: "+id, HttpStatus.NOT_FOUND));
        if(categoryRepository.findByName(updateCategoryNameCommand.name()).isPresent()){
           throw new ErrorNegocio("El nombre " + updateCategoryNameCommand.name() + " ya esta en uso",HttpStatus.CONFLICT);
        }
        category.setName(updateCategoryNameCommand.name());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        var category = categoryRepository.findById(id).orElseThrow(()-> new ErrorNegocio("No existe la categoria con id: "+id, HttpStatus.NOT_FOUND));
        categoryRepository.delete(category);
    }

}
