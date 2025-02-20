package com.br.neoGenesis.domain.service;

import com.br.neoGenesis.domain.model.Game;
import com.br.neoGenesis.domain.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    private final GameRepository gameRepo;

    public GameService(GameRepository gameRepo){
        this.gameRepo = gameRepo;
    }
    public List<Game> listGames(){
        return gameRepo.findAll();
    }
    public List<Game> listDisponibleGames(){
        return gameRepo.findByDisponibleTrue();
    }
    public Game findByID(UUID id){
        return gameRepo.findById(id).orElseThrow(() -> new RuntimeException("Jogo não encontrado!"));
    }
    public Game saveGame(Game game){
        return gameRepo.save(game);
    }
    public void removeGame(UUID id){
        gameRepo.deleteById(id);
    }
    public Game updateGame(UUID id, Game updatedGame) {
        return gameRepo.findById(id)
                .map(existingGame -> {
                    existingGame.setTitle(updatedGame.getTitle());
                    existingGame.setPlatform(updatedGame.getPlatform());
                    existingGame.setPriceRent(updatedGame.getPriceRent());
                    existingGame.setDisponible(updatedGame.isDisponible());
                    return gameRepo.save(existingGame);
                })
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }
}
