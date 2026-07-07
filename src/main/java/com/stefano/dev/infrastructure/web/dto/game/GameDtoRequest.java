package com.stefano.dev.infrastructure.web.dto.game;


import java.util.Set;

public record GameDtoRequest (
        String name,
        String description,
        Double price,
        Set<Integer> categories
){
}
