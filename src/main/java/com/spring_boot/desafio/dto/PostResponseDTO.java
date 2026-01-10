package com.spring_boot.desafio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostResponseDTO(String message,
                              @JsonProperty("user_count")
                              long userCount) {
}
