package com.stefano.dev.infrastructure.persistence.repository;


import com.stefano.dev.infrastructure.persistence.entity.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GameJPARepository extends JpaRepository<GameEntity, Integer> {
    Optional<GameEntity> findByIdAndIsActiveTrue(Integer id);
    Page<GameEntity> findAllByIsActiveTrue(Pageable pageable);
    Page<GameEntity> findByNameContainingAndIsActiveTrue(String name, Pageable pageable);

}
