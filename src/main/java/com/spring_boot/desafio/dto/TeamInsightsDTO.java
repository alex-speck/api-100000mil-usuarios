package com.spring_boot.desafio.dto;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TeamInsightsDTO {

    private String team;
    private long totalMembers;
    private long leaders;
    private long completedProjects;
    private double activePercentage;

    public TeamInsightsDTO(
            String team,
            Number totalMembers,
            Number leaders,
            Number completedProjects,
            Number activePercentage
    ) {
        this.team = team;
        this.totalMembers = totalMembers.longValue();
        this.leaders = leaders.longValue();
        this.completedProjects = completedProjects.longValue();
        this.activePercentage = activePercentage.doubleValue();
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public long getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(long totalMembers) {
        this.totalMembers = totalMembers;
    }

    public long getLeaders() {
        return leaders;
    }

    public void setLeaders(long leaders) {
        this.leaders = leaders;
    }

    public long getCompletedProjects() {
        return completedProjects;
    }

    public void setCompletedProjects(long completedProjects) {
        this.completedProjects = completedProjects;
    }

    public double getActivePercentage() {
        return activePercentage;
    }

    public void setActivePercentage(double activePercentage) {
        this.activePercentage = activePercentage;
    }
}
