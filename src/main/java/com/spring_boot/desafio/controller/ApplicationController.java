package com.spring_boot.desafio.controller;

import com.spring_boot.desafio.dto.PostResponseDTO;
import com.spring_boot.desafio.dto.ResponseDTO;
import com.spring_boot.desafio.dto.UsuarioRequestDTO;
import com.spring_boot.desafio.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ApplicationController {

    private final UsuarioService service;

    public ApplicationController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<PostResponseDTO> salvarUsuarios(@RequestBody List<UsuarioRequestDTO> usuarios){
        try {
            return ResponseEntity.ok(service.salvarUsuarios(usuarios));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/superusers")
    public ResponseEntity<ResponseDTO> getSuperstar(@RequestParam(value = "s", defaultValue = "900") int score){
        try {
            return ResponseEntity.ok(service.superusers(score));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/top-countries")
    public ResponseEntity<ResponseDTO> getTopCountries(){
        try {
            return ResponseEntity.ok(service.topCountries());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/team-insights")
    public ResponseEntity<ResponseDTO> getTeamInsight(){
        try {
            return ResponseEntity.ok(service.getTeamInsights());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/active-users-per-day")
    public ResponseEntity<ResponseDTO> getUsersPerDay(@RequestParam(value = "min", defaultValue = "3000") int min){
        try {
            return ResponseEntity.ok(service.getLoginsPerDay(min));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
