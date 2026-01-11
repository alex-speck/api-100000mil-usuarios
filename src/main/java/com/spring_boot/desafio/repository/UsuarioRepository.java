package com.spring_boot.desafio.repository;

import com.spring_boot.desafio.dto.LoginsPerDayDTO;
import com.spring_boot.desafio.dto.TeamInsightsDTO;
import com.spring_boot.desafio.dto.TopCountriesDTO;
import com.spring_boot.desafio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    @Query("""
            SELECT u
            FROM Usuario u
            WHERE u.score >= :score AND u.active = true
            ORDER BY u.score
            """)
    List<Usuario> findAllActiveAndWithScoreOf(@Param("score") int score);

    @Query("""
            SELECT u.country, count(u)
            FROM Usuario u
            WHERE u.score >= 900
            GROUP BY u.country
            """)
    List<TopCountriesDTO> filterSuperusersByCountry();

    @Query(value = """
            SELECT l.date, SUM(CASE WHEN l.action = 0 THEN 1 ELSE 0 END) AS total
            FROM Usuario_Logs as l
            GROUP BY l.date
            HAVING SUM(CASE WHEN l.action = 0 THEN 1 ELSE 0 END) > :min
            """, nativeQuery = true)
    List<LoginsPerDayDTO> getLoginsPerDay(@Param(value = "min") int min);
}
