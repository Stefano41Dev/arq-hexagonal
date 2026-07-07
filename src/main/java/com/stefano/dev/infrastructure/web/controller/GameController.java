package com.stefano.dev.infrastructure.web.controller;

import com.stefano.dev.application.command.game.SaveGameCommand;
import com.stefano.dev.application.service.CategoryService;
import com.stefano.dev.application.service.GameService;
import com.stefano.dev.infrastructure.web.dto.category.CategoryDtoResponse;
import com.stefano.dev.infrastructure.web.dto.game.GameDtoRequest;
import com.stefano.dev.infrastructure.web.dto.game.GameDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
