package com.stefano.dev.infrastructure.persistence.adapter;

import com.stefano.dev.domain.model.Category;
import com.stefano.dev.domain.model.Game;
import com.stefano.dev.domain.pagination.PageRequest;
import com.stefano.dev.domain.pagination.PageResult;
import com.stefano.dev.domain.port.GameRepository;
import com.stefano.dev.infrastructure.persistence.entity.CategoryEntity;
import com.stefano.dev.infrastructure.persistence.entity.GameEntity;
import com.stefano.dev.infrastructure.persistence.repository.GameJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .price(game.getPrice())
                .isActive(game.getIsActive())
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
        return gameJPARepository.findByIdAndIsActiveTrue(id).map(gameEntity -> Game.builder()
                .id(gameEntity.getId())
                .name(gameEntity.getName())
                .description(gameEntity.getDescription())
                .price(gameEntity.getPrice())
                .categories(gameEntity.getCategories().stream().map(category -> Category.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build()).collect(Collectors.toSet()))
                .createAt(gameEntity.getCreateAt())
                .isActive(gameEntity.getIsActive())
                .build());
    }

    @Override
    public PageResult<Game> findByName(String name, PageRequest pageRequest) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                pageRequest.page(),
                pageRequest.size()
        );
        Page<GameEntity> page = gameJPARepository.findByNameContainingAndIsActiveTrue(name, pageable);

        return new PageResult<>(
                page.getContent()
                        .stream()
                        .map(game-> Game.builder()
                                .id(game.getId())
                                .name(game.getName())
                                .description(game.getDescription())
                                .price(game.getPrice())
                                .categories(game.getCategories().stream().map(category -> Category.builder()
                                        .id(category.getId())
                                        .name(category.getName())
                                        .build()).collect(Collectors.toSet()))
                                .createAt(game.getCreateAt())
                                .isActive(game.getIsActive())
                                .build())
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    @Override
    public PageResult<Game> list(PageRequest pageRequest) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                        pageRequest.page(),
                        pageRequest.size()
                );
        Page<GameEntity> page = gameJPARepository.findAllByIsActiveTrue(pageable);


        return new PageResult<>(
                page.getContent()
                        .stream()
                        .map(game-> Game.builder()
                                .id(game.getId())
                                .name(game.getName())
                                .description(game.getDescription())
                                .price(game.getPrice())
                                .categories(game.getCategories().stream().map(category -> Category.builder()
                                        .id(category.getId())
                                        .name(category.getName())
                                        .build()).collect(Collectors.toSet()))
                                .createAt(game.getCreateAt())
                                .isActive(game.getIsActive())
                                .build())
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
