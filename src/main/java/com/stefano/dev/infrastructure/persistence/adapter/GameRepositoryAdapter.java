package com.stefano.dev.infrastructure.persistence.adapter;

import com.stefano.dev.domain.model.Category;
import com.stefano.dev.domain.model.Game;
import com.stefano.dev.domain.pagination.PageResult;
import com.stefano.dev.domain.port.GameRepository;
import com.stefano.dev.infrastructure.persistence.entity.CategoryEntity;
import com.stefano.dev.infrastructure.persistence.entity.GameEntity;
import com.stefano.dev.infrastructure.persistence.repository.GameJPARepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository

public class GameRepositoryAdapter implements GameRepository {
    private final GameJPARepository gameJPARepository;

    public GameRepositoryAdapter(GameJPARepository gameJPARepository){
        this.gameJPARepository = gameJPARepository;
    }
    @Override
    public Game save(Game game) {
        var saveEntity = gameJPARepository.save(GameEntity.builder()
                .name(game.getName())
                .description(game.getDescription())
                .price(game.getPrice())
                .categories(game.getCategories().stream().map(category -> CategoryEntity.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build()).collect(Collectors.toSet()))
                .build());

        return Game.builder()
                .id(saveEntity.getId())
                .name(saveEntity.getName())
                .description(saveEntity.getDescription())
                .price(saveEntity.getPrice())
                .categories(saveEntity.getCategories().stream().map(category -> Category.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build()).collect(Collectors.toSet()))
                .createAt(saveEntity.getCreateAt())
                .isActive(game.getIsActive())
                .build() ;
    }

    @Override
    public Optional<Game> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public PageResult<Game> findByName(String name) {
        return null;
    }

    @Override
    public PageResult<Game> list() {
        return null;
    }

    @Override
    public Game update(Game game) {
        return null;
    }

    @Override
    public Void delete(Integer id) {
        return null;
    }
}
