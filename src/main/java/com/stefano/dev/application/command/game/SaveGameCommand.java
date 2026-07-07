package com.stefano.dev.application.command.game;

import lombok.Builder;

import java.util.Set;
@Builder
public record SaveGameCommand(
        String name,
        String description,
        Double price,
        Set<Integer> categoriesId
) {
}
