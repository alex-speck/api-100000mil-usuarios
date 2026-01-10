package com.spring_boot.desafio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseDTO<T>(LocalDateTime timestamp,
                             @JsonProperty("execution_time_ms")
                             Long executionTimeMs,
                             T data) {
}
