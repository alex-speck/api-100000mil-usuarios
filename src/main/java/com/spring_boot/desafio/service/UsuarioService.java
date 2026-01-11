package com.spring_boot.desafio.service;

import com.spring_boot.desafio.dto.*;
import com.spring_boot.desafio.model.Team;
import com.spring_boot.desafio.model.Usuario;
import com.spring_boot.desafio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final TeamService teamService;


    public UsuarioService(UsuarioRepository repository, TeamService teamService) {
        this.repository = repository;
        this.teamService = teamService;
    }


    public PostResponseDTO salvarUsuarios(List<UsuarioRequestDTO> usuariosRequest) {
        List<Team> teamsList = teamService.saveAll(usuariosRequest.stream().map(UsuarioRequestDTO::team).toList());
        System.out.println(teamsList);
        List<Usuario> usuarios = usuariosRequest.stream().map(this::toEntity).toList();
        repository.saveAll(usuarios);
        return new PostResponseDTO("Arquivo recebido com sucesso", repository.count());
    }

    public ResponseDTO<List<UsuarioResponseDTO>> superusers(int score){
        LocalDateTime now = timestamp();
        List<UsuarioResponseDTO> usuarios = repository.findAllActiveAndWithScoreOf(score).stream().map(this::toRespose).toList();
        return new ResponseDTO<>(now, executionTime(now), usuarios);
    }

    public ResponseDTO<List<TopCountriesDTO>> topCountries(){
        LocalDateTime now = timestamp();
        List<TopCountriesDTO> contagem = repository.filterSuperusersByCountry().stream().limit(5).toList();
        return new ResponseDTO<>(now, executionTime(now), contagem);
    }

    public ResponseDTO<List<TeamInsightsDTO>> getTeamInsights(){
        LocalDateTime now = timestamp();
        List<TeamInsightsDTO> contagem = teamService.getTeamInsight();
        return new ResponseDTO<>(now, executionTime(now), contagem);
    }

    public ResponseDTO<List<LoginsPerDayDTO>> getLoginsPerDay(int min){
        LocalDateTime now = timestamp();
        List<LoginsPerDayDTO> contagem = repository.getLoginsPerDay(min);
        return new ResponseDTO<>(now, executionTime(now), contagem);
    }


    private Usuario toEntity(UsuarioRequestDTO dto){
        Team team = teamService.findByName(dto.team().name());
        return new Usuario(
                dto.id(),
                dto.name(),
                dto.age(),
                dto.score(),
                dto.active(),
                dto.country(),
                team,
                dto.team().leader(),
                dto.logs()
        );
    }

    private LocalDateTime timestamp(){
        return LocalDateTime.now(ZoneId.systemDefault());
    }

    private long executionTime(LocalDateTime start) {
        return LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli() - start.atZone(ZoneId.systemDefault())
                                                    .toInstant()
                                                    .toEpochMilli();
    }

    private UsuarioResponseDTO toRespose(Usuario u){
        return new UsuarioResponseDTO(
                u.getId(),
                u.getName(),
                u.getAge(),
                u.getScore(),
                u.isActive(),
                u.getCountry(),
                new TeamRequestDTO(u.getTeam().getName(), u.isLeader(), u.getTeam().getProjects()),
                u.getLogs());
    }
}
