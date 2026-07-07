package com.stefano.dev.infrastructure.web.dto.category;

import lombok.Builder;

@Builder
public record CategoryDtoResponse(
        Integer id,
        String name

) {
}
