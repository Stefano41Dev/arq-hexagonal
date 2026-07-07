package com.stefano.dev.application.usecase.game;

import com.stefano.dev.domain.model.Game;
import com.stefano.dev.domain.pagination.PageResult;



public interface FindByNameGameUseCase {
    PageResult<Game> findByName(String name);
}
