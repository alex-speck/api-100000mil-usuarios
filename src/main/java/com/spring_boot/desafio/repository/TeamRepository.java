package com.spring_boot.desafio.repository;

import com.spring_boot.desafio.model.Logs;
import com.spring_boot.desafio.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
