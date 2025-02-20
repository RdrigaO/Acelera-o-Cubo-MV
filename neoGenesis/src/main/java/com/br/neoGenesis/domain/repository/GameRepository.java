package com.br.neoGenesis.domain.repository;

import com.br.neoGenesis.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
    List<Game> findByDisponibleTrue();
}
