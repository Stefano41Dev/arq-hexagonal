package com.stefano.dev.application.usecase.category;

import com.stefano.dev.application.command.category.SaveCategoryCommand;
import com.stefano.dev.application.command.category.UpdateCategoryNameCommand;
import com.stefano.dev.domain.model.Category;

import java.util.List;

public interface CategoryUseCase {
    Category save (SaveCategoryCommand saveCategoryCommand);
    Category findById(Integer id);
    List<Category> findByName(String name);
    List<Category> list();
    Category update(Integer id, UpdateCategoryNameCommand updateCategoryNameCommand);
    void delete(Integer id);
}
