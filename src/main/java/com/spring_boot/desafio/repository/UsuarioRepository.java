package com.spring_boot.desafio.repository;

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
            """)
    List<Usuario> findAllActiveAndWithScoreOf(@Param("score") int score);

    @Query("""
            SELECT u.country, count(u)
            FROM Usuario u
            WHERE u.score >= 900
            GROUP BY u.country
            """)
    List<TopCountriesDTO> filterSuperusersByCountry();
}
