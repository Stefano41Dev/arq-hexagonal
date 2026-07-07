package com.stefano.dev.application.usecase.game;

import com.stefano.dev.domain.model.Game;

public interface FindByIdGameUseCase {
    Game findById(Integer id);
}
