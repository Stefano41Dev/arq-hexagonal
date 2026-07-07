package com.stefano.dev.application.usecase.game;

import com.stefano.dev.application.command.game.SaveGameCommand;
import com.stefano.dev.domain.model.Game;

public interface UpdateGameUseCase {
    Game update(Integer id, SaveGameCommand update);
}
