package com.stefano.dev.infrastructure.web.controller;

import com.stefano.dev.application.command.category.SaveCategoryCommand;
import com.stefano.dev.application.command.category.UpdateCategoryNameCommand;
import com.stefano.dev.application.service.CategoryService;
import com.stefano.dev.infrastructure.web.dto.category.CategoryDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> list(){
        var list = categoryService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<?> findCategoryById(
            @PathVariable("idCategory") Integer id
    ){
        var category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("search/{name}")
    public ResponseEntity<?> findCategoryByName(
            @PathVariable String name
    ){
        var listCategory = categoryService.findByName(name);
        return ResponseEntity.ok(listCategory);
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody CategoryDtoRequest categoryDtoRequest
    ){
        SaveCategoryCommand saveCategoryCommand = new SaveCategoryCommand(categoryDtoRequest.name());
        var saveResponse = categoryService.save(saveCategoryCommand);
        return ResponseEntity.ok(saveResponse);
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<?> update(
            @PathVariable Integer idCategory,
            @RequestBody CategoryDtoRequest categoryDtoRequest
    ){
        UpdateCategoryNameCommand updateCategoryNameCommand = new UpdateCategoryNameCommand(categoryDtoRequest.name());
        var updateResponse = categoryService.update(idCategory, updateCategoryNameCommand);
        return ResponseEntity.ok(updateResponse);
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<?> delete(
        @PathVariable Integer idCategory
    ){
        categoryService.delete(idCategory);
        return ResponseEntity.noContent().build();
    }

}
