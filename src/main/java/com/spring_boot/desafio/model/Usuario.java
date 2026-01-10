package com.spring_boot.desafio.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private UUID id;
    private String name;
    private int age;
    private int score;
    private boolean active;
    private String country;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    private Team team;
    @ElementCollection
    private List<Logs> logs;

    public Usuario() {
    }

    public Usuario(UUID id, String name, int age, int score, boolean active, String country, Team team, List<Logs> logs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.active = active;
        this.country = country;
        this.team = team;
        this.logs = logs;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Logs> getLogs() {
        return logs;
    }

    public void setLogs(List<Logs> logs) {
        this.logs = logs;
    }
}
