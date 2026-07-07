package com.stefano.dev.infrastructure.web.dto.game;

import com.stefano.dev.infrastructure.web.dto.category.CategoryDtoResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;
@Builder
public record GameDtoResponse(
        Integer id,
        String name,
        String description,
        Double price,
        Set<CategoryDtoResponse> categories,
        LocalDate createAt
) {
}
