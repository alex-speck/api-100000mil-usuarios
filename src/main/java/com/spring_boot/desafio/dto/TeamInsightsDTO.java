package com.spring_boot.desafio.dto;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TeamInsightsDTO(String team,
                              long totalMembers,
                              long leaders,
                              long completedProjects,
                              float activePercentage) {
}
