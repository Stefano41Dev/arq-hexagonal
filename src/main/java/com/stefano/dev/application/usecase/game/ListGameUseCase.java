package com.stefano.dev.application.usecase.game;

import com.stefano.dev.domain.model.Game;
import com.stefano.dev.domain.pagination.PageRequest;
import com.stefano.dev.domain.pagination.PageResult;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;

public interface ListGameUseCase {
    PageResult<Game> list(PageRequest pageRequest);

}
