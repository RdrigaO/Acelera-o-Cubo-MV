package com.br.neoGenesis.domain.repository;

import com.br.neoGenesis.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByDisponibleTrue();
}
