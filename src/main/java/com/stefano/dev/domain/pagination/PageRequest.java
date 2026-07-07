package com.stefano.dev.domain.pagination;

public record PageRequest(
        int page,
        int size
) {
}
