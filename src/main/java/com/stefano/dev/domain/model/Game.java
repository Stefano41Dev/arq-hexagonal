package com.stefano.dev.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
public class Game {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Set<Category> categories;
    @Builder.Default
    private LocalDate createAt = LocalDate.now();
    private Boolean isActive;
}
