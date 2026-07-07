package com.stefano.dev.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "game_categories",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories = new HashSet<>();
    @Builder.Default
    private LocalDate createAt = LocalDate.now();
    @Builder.Default
    private Boolean isActive = true;
}
