package com.wiwu.bookflow.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        LocalDateTime timestamp,
        Integer status,
        String message,
        Map<String,String> erros
) {
}
