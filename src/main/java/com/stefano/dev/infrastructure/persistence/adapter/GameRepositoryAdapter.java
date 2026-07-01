package com.stefano.dev.infrastructure.persistence.adapter;

import com.stefano.dev.domain.model.Game;
import com.stefano.dev.domain.port.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class GameRepositoryAdapter implements GameRepository {
    @Override
    public Game save(Game game) {
        return null;
    }

    @Override
    public Optional<Game> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Game> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Game> list() {
        return List.of();
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
