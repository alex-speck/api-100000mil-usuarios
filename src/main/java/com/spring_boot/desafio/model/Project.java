package com.spring_boot.desafio.model;

import jakarta.persistence.*;

@Embeddable
public class Project {

    private String name;
    private boolean completed;


    public Project() {
    }

    public Project(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
