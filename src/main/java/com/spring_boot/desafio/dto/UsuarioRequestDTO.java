package com.spring_boot.desafio.dto;

import com.spring_boot.desafio.model.Logs;


import java.util.List;
import java.util.UUID;

public record UsuarioRequestDTO(UUID id,
                                String name,
                                int age,
                                int score,
                                boolean active,
                                String country,
                                TeamRequestDTO team,
                                List<Logs> logs) {

}
