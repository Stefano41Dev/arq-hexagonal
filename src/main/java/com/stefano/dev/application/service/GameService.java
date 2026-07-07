package com.stefano.dev.application.service;

import com.stefano.dev.application.command.game.SaveGameCommand;
import com.stefano.dev.application.usecase.game.*;
import com.stefano.dev.domain.exception.ErrorNegocio;
import com.stefano.dev.domain.model.Category;
import com.stefano.dev.domain.model.Game;
import com.stefano.dev.domain.pagination.PageResult;
import com.stefano.dev.domain.port.CategoryRepository;
import com.stefano.dev.domain.port.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService implements SaveGameUseCase, DeleteGameUseCase, FindByIdGameUseCase, FindByNameGameUseCase, ListGameUseCase, UpdateGameUseCase {
    // Todo - Nota: Segunda forma de crear un useCase, por separado cara caso de uso, proyectos medianos o grandes
    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;

    public GameService(GameRepository gameRepository, CategoryRepository categoryRepository){
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Game save(SaveGameCommand saveGameCommand) {


        Set<Category> categories = saveGameCommand.categoriesId().stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new ErrorNegocio("Categoría no encontrada: " + id, HttpStatus.NOT_FOUND)))
                .collect(Collectors.toSet());

        return gameRepository.save(Game.builder()
                .name(saveGameCommand.name())
                .description(saveGameCommand.description())
                .price(saveGameCommand.price())
                .categories(categories)
                .build());
    }


    @Override
    public void delete(Integer id) {
        var gameDelete = gameRepository.findById(id).orElseThrow();
        gameDelete.setIsActive(false);
        gameRepository.save(gameDelete);
    }

    @Override
    public Game findById(Integer id) {
        return gameRepository.findById(id).orElseThrow(()-> new ErrorNegocio("No existe el juego con id: "+id, HttpStatus.NOT_FOUND));
    }

    @Override
    public PageResult<Game> findByName(String name) {

        return gameRepository.findByName(name);
    }

    @Override
    public Game update(Integer id, SaveGameCommand update) {
        var gameUpdate = gameRepository.findById(id).orElseThrow();

        Set<Category> categories = update.categoriesId().stream()
                .map(idCate -> categoryRepository.findById(idCate)
                        .orElseThrow(() -> new ErrorNegocio("Categoría no encontrada: " + idCate, HttpStatus.NOT_FOUND)))
                .collect(Collectors.toSet());

        gameUpdate.setName(update.name());
        gameUpdate.setCategories(categories);
        gameUpdate.setDescription(update.description());
        gameUpdate.setPrice(update.price());

        return gameRepository.save(gameUpdate);
    }

    @Override
    public PageResult<Game> list() {
        return gameRepository.list();
    }
}
