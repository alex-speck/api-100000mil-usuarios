package com.spring_boot.desafio.service;

import com.spring_boot.desafio.dto.TeamInsightsDTO;
import com.spring_boot.desafio.dto.TeamRequestDTO;
import com.spring_boot.desafio.model.Team;
import com.spring_boot.desafio.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Team> saveAll(List<TeamRequestDTO> teams) {
        teams.stream().forEach((team) -> {
            if (!repository.existsByName(team.name())) {
                repository.save(new Team(team.name(), team.projects()));
            }
        });

        return repository.findAll();
    }

    public Team findByName(String name){
        return repository.findByName(name).get();
    }

    public List<TeamInsightsDTO> getTeamInsight(){
        return repository.getTeamInsights();
    }
}
