package com.stefano.dev.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.HashSet;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "categories")
    private HashSet<GameEntity> games = new HashSet<>();

}
