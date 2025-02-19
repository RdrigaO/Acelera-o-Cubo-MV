package com.br.neoGenesis.domain.service;

import com.br.neoGenesis.domain.model.Game;
import com.br.neoGenesis.domain.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Game findByID(Long id){
        return gameRepo.findById(id).orElseThrow(() -> new RuntimeException("Jogo não encontrado!"));
    }
    public Game saveGame(Game game){
        return gameRepo.save(game);
    }
    public void removeGame(long id){
        gameRepo.deleteById(id);
    }
}
