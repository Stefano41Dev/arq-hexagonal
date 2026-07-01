package com.stefano.dev.domain.exception;

import java.time.LocalDateTime;

public record ErrorDto(
        String message,
        LocalDateTime currentDate
) {

}
