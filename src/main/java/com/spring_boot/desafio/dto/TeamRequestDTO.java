package com.spring_boot.desafio.dto;

import com.spring_boot.desafio.model.Project;

import java.util.List;

public record TeamRequestDTO(String name,
                             boolean leader,
                             List<Project> projects) {
}
