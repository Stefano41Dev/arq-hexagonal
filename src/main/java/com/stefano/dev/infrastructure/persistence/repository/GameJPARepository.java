package com.stefano.dev.infrastructure.persistence.repository;

import com.stefano.dev.infrastructure.persistence.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameJPARepository extends JpaRepository<GameEntity, Integer> {
}
