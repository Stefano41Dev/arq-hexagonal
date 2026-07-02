package com.stefano.dev.infrastructure.web.controller;

import com.stefano.dev.application.command.category.SaveCategoryCommand;
import com.stefano.dev.application.command.category.UpdateCategoryNameCommand;
import com.stefano.dev.application.service.CategoryService;
import com.stefano.dev.infrastructure.web.dto.category.CategoriaDtoResponse;
import com.stefano.dev.infrastructure.web.dto.category.CategoryDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    // Todo: Arreglar que liste solo los activo
    @GetMapping
    public ResponseEntity<List<CategoriaDtoResponse>> list(){
        var list = categoryService.list();
        return ResponseEntity.ok(list.stream().map(category-> CategoriaDtoResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build()).toList());
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoriaDtoResponse> findCategoryById(
            @PathVariable("idCategory") Integer id
    ){
        var category = categoryService.findById(id);
        return ResponseEntity.ok(CategoriaDtoResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build());
    }

    @GetMapping("search/{name}")
    public ResponseEntity<List<CategoriaDtoResponse>> findCategoryByName(
            @PathVariable String name
    ){
        var listCategory = categoryService.findByName(name);
        return ResponseEntity.ok(listCategory.stream().map(category -> CategoriaDtoResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build()).toList());
    }

    @PostMapping
    public ResponseEntity<CategoriaDtoResponse> save(
            @RequestBody CategoryDtoRequest categoryDtoRequest
    ){
        SaveCategoryCommand saveCategoryCommand = new SaveCategoryCommand(categoryDtoRequest.name());
        var saveResponse = categoryService.save(saveCategoryCommand);
        return ResponseEntity.ok(CategoriaDtoResponse.builder()
                .id(saveResponse.getId())
                .name(saveResponse.getName())
                .build());
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<CategoriaDtoResponse> update(
            @PathVariable Integer idCategory,
            @RequestBody CategoryDtoRequest categoryDtoRequest
    ){
        UpdateCategoryNameCommand updateCategoryNameCommand = new UpdateCategoryNameCommand(categoryDtoRequest.name());
        var updateResponse = categoryService.update(idCategory, updateCategoryNameCommand);
        return ResponseEntity.ok(CategoriaDtoResponse.builder()
                .id(updateResponse.getId())
                .name(updateResponse.getName())
                .build());
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<?> delete(
        @PathVariable Integer idCategory
    ){
        categoryService.delete(idCategory);
        return ResponseEntity.noContent().build();
    }

}
