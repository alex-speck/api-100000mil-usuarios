package com.spring_boot.desafio.service;

import com.spring_boot.desafio.model.Team;
import com.spring_boot.desafio.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> saveAll(List<Team> teams) {
        return repository.saveAll(teams);
    }
}
