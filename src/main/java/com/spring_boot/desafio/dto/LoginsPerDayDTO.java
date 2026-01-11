package com.spring_boot.desafio.dto;

import java.time.LocalDate;

public record LoginsPerDayDTO(LocalDate date,
                              long total) {
}
