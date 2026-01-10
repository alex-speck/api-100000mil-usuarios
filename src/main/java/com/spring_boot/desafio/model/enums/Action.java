package com.spring_boot.desafio.model.enums;

public enum Action {
    login("login"),
    logout("logout");

    private final String action;

    Action(String action) {
        this.action = action;
    }

    public String getAction(){
        return action;
    }
}
