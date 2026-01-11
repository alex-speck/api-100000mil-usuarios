package com.spring_boot.desafio.repository;

import com.spring_boot.desafio.dto.TeamInsightsDTO;
import com.spring_boot.desafio.model.Logs;
import com.spring_boot.desafio.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByName(String name);
    Optional<Team> findByName(String name);

    @Query(value = """
            SELECT
                t.name AS team,
                COUNT(DISTINCT u.id) AS totalMembers,
                COUNT(DISTINCT CASE WHEN u.leader = true THEN u.id END) AS leaders,
                COUNT(DISTINCT CASE WHEN p.completed = true THEN 1 ELSE 0 END) AS completedProjects,
                (COUNT(DISTINCT CASE WHEN u.active = true THEN u.id END) * 100.0
                    / COUNT(DISTINCT u.id)) AS activePercentage
            FROM Team t
            INNER JOIN Usuarios u ON u.team_id = t.id
            LEFT JOIN Team_Projects p ON p.team_id = t.id
            GROUP BY t.name;
            """, nativeQuery = true)
    List<TeamInsightsDTO> getTeamInsights();
}
