package com.stefano.dev.infrastructure.web.dto.category;

import lombok.Builder;

@Builder
public record CategoriaDtoResponse(
        Integer id,
        String name

) {
}
