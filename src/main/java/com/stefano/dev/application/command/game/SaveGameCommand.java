package com.stefano.dev.application.command.game;

public record SaveGameCommand(
        String name,
        String description,
        Double price,
        Integer categoryId
) {
}
