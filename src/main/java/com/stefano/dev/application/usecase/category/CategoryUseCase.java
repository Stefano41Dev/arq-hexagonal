package com.stefano.dev.application.usecase.category;

import com.stefano.dev.application.command.category.SaveCategoryCommand;
import com.stefano.dev.domain.model.Category;

public interface CategoryUseCase {
    Category save (SaveCategoryCommand saveCategoryCommand);
}
