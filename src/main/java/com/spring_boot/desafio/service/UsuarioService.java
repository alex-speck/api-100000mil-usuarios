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
        List<Team> teams = teamService.saveAll(usuariosRequest.stream().map(UsuarioRequestDTO::team).toList());
        List<Usuario> usuarios = usuariosRequest.stream().map(this::toEntity).toList();

        return new PostResponseDTO("Arquivo recebido com sucesso", repository.count());
    }

    public ResponseDTO<List<Usuario>> superusers(int score){
        LocalDateTime now = timestamp();
        List<Usuario> usuarios = repository.findAllActiveAndWithScoreOf(score);
        return new ResponseDTO<>(now, executionTime(now), usuarios);
    }

    public ResponseDTO<List<TopCountriesDTO>> topCountries(){
        LocalDateTime now = timestamp();
        List<TopCountriesDTO> contagem = repository.filterSuperusersByCountry().stream().limit(5).toList();
        return new ResponseDTO<>(now, executionTime(now), contagem);
    }

    public ResponseDTO<List<TeamInsightsDTO>> getTeamInsights(){

    }

    private Usuario toEntity(UsuarioRequestDTO dto){
        return new Usuario(
                dto.id(),
                dto.name(),
                dto.age(),
                dto.score(),
                dto.active(),
                dto.country(),
                dto.team(),
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
}
