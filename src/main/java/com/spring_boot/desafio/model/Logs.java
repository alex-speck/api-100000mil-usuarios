package com.spring_boot.desafio.model;

import com.spring_boot.desafio.model.enums.Action;
import jakarta.persistence.*;

import java.time.LocalDate;

// @Entity
@Embeddable
public class Logs {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // private Long id;

    private LocalDate date;
    private Action action;

   //  public Long getId() {
   //     return id;
   // }

   // public void setId(Long id) {
   //     this.id = id;
   // }


    public Logs() {
    }

    public Logs(LocalDate date, Action action) {
        this.date = date;
        this.action = action;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
