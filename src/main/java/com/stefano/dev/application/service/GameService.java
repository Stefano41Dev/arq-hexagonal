package com.stefano.dev.application.service;

import com.stefano.dev.application.command.game.SaveGameCommand;
import com.stefano.dev.application.usecase.game.SaveGameUseCase;
import com.stefano.dev.domain.model.Category;
import com.stefano.dev.domain.model.Game;
import com.stefano.dev.domain.port.CategoryRepository;
import com.stefano.dev.domain.port.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService implements SaveGameUseCase {
    // Todo - Nota: Segunda forma de crear un useCase, por separado cara caso de uso, proyectos medianos o grandes
    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;

    public GameService(GameRepository gameRepository, CategoryRepository categoryRepository){
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Game save(SaveGameCommand saveGameCommand) {
        categoryRepository.findByName(saveGameCommand.name()).orElseThrow();

        Category category = categoryRepository.findById(saveGameCommand.categoryId()).orElseThrow();

        return gameRepository.save(Game.builder()
                .name(saveGameCommand.name())
                .description(saveGameCommand.description())
                .price(saveGameCommand.price())
                .category(category)
                .build());
    }
}
