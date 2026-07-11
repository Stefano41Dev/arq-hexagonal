package com.stefano.dev.infrastructure.web.controller;

import com.stefano.dev.application.command.game.SaveGameCommand;
import com.stefano.dev.application.service.CategoryService;
import com.stefano.dev.application.service.GameService;
import com.stefano.dev.domain.model.Game;
import com.stefano.dev.domain.pagination.PageRequest;
import com.stefano.dev.domain.pagination.PageResult;
import com.stefano.dev.infrastructure.web.dto.category.CategoryDtoResponse;
import com.stefano.dev.infrastructure.web.dto.game.GameDtoRequest;
import com.stefano.dev.infrastructure.web.dto.game.GameDtoResponse;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;
    private final CategoryService categoryService;

    public GameController(GameService gameService, CategoryService categoryService){
        this.gameService = gameService;
        this.categoryService = categoryService;
    }
    @PostMapping
    public ResponseEntity<GameDtoResponse> save(
            @RequestBody GameDtoRequest gameDtoRequest){
       var saveGame = gameService.save(SaveGameCommand.builder()
               .name(gameDtoRequest.name())
               .description(gameDtoRequest.description())
               .price(gameDtoRequest.price())
               .categoriesId(gameDtoRequest.categories())
               .build());

       return ResponseEntity.ok(GameDtoResponse.builder()
               .id(saveGame.getId())
               .name(saveGame.getName())
               .description(saveGame.getDescription())
               .price(saveGame.getPrice())
               .categories(saveGame.getCategories().stream().map(cate-> {
                   var categoryEntity = categoryService.findById(cate.getId());
                   return CategoryDtoResponse.builder().id(categoryEntity.getId()).name(categoryEntity.getName()).build();
               }).collect(Collectors.toSet()))
               .createAt(saveGame.getCreateAt())
               .build());
    }

    @GetMapping
    public ResponseEntity<PageResult<GameDtoResponse>> list(
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        var pageRequest = new PageRequest(
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        PageResult<Game> page = gameService.list(pageRequest);

        PageResult<GameDtoResponse> response = new PageResult<>(
                page.content().stream()
                        .map(game -> GameDtoResponse.builder()
                                .id(game.getId())
                                .name(game.getName())
                                .description(game.getDescription())
                                .price(game.getPrice())
                                .categories(
                                        game.getCategories().stream()
                                                .map(category -> CategoryDtoResponse.builder()
                                                        .id(category.getId())
                                                        .name(category.getName())
                                                        .build())
                                                .collect(Collectors.toSet())
                                )
                                .createAt(game.getCreateAt())
                                .build())
                        .toList(),
                page.page(),
                page.size(),
                page.totalElements(),
                page.totalPages()
        );

        return ResponseEntity.ok(response);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<PageResult<GameDtoResponse>> findByName(
            @PathVariable String name,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ){
        var pageRequest = new PageRequest(
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
        PageResult<Game> page = gameService.findByName(name,pageRequest);

        PageResult<GameDtoResponse> response = new PageResult<>(
                page.content().stream()
                        .map(game -> GameDtoResponse.builder()
                                .id(game.getId())
                                .name(game.getName())
                                .description(game.getDescription())
                                .price(game.getPrice())
                                .categories(
                                        game.getCategories().stream()
                                                .map(category -> CategoryDtoResponse.builder()
                                                        .id(category.getId())
                                                        .name(category.getName())
                                                        .build())
                                                .collect(Collectors.toSet())
                                )
                                .createAt(game.getCreateAt())
                                .build())
                        .toList(),
                page.page(),
                page.size(),
                page.totalElements(),
                page.totalPages()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDtoResponse> findById(
            @PathVariable Integer id
    ){
        var game = gameService.findById(id);
        return ResponseEntity.ok(GameDtoResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .price(game.getPrice())
                .categories(game.getCategories().stream().map(cate-> {
                    var categoryEntity = categoryService.findById(cate.getId());
                    return CategoryDtoResponse.builder().id(categoryEntity.getId()).name(categoryEntity.getName()).build();
                }).collect(Collectors.toSet()))
                .createAt(game.getCreateAt())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDtoResponse> update(
            @PathVariable Integer id,
            @RequestBody GameDtoRequest updateRequest
    ){


        var game = gameService.update(id,SaveGameCommand.builder()
                .name(updateRequest.name())
                .description(updateRequest.description())
                .price(updateRequest.price())
                .categoriesId(updateRequest.categories())
                .build());

        return ResponseEntity.ok(GameDtoResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .price(game.getPrice())
                .categories(game.getCategories().stream().map(cate-> {
                    var categoryEntity = categoryService.findById(cate.getId());
                    return CategoryDtoResponse.builder().id(categoryEntity.getId()).name(categoryEntity.getName()).build();
                }).collect(Collectors.toSet()))
                .createAt(game.getCreateAt())
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable Integer id
    ){
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
