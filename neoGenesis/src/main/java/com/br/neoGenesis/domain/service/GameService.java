package com.br.neoGenesis.domain.service;

import com.br.neoGenesis.api.exception.GameNotFoundException;
import com.br.neoGenesis.api.exception.InvalidGameException;
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
        return gameRepo.findById(id).orElseThrow(() -> new GameNotFoundException("Jogo não encontrado com o Id: " + id));
    }
    public Game saveGame(Game game) {
        if (game == null) throw new InvalidGameException("O jogo não pode ser nulo.");
        if (game.getTitle() == null || game.getTitle().isEmpty())
            throw new InvalidGameException("O titulo do jogo não pode ser vazio.");
        if (game.getPlatform() == null || game.getPlatform().isEmpty())
            throw new InvalidGameException("O tipo de plataforma do jogo não pode ser vazio.");
        return gameRepo.save(game);
    }
    public void removeGame(UUID id){
        if(!gameRepo.existsById(id)){
            throw new GameNotFoundException("Remoção não realizada, verifique o ID: " + id);
        }
        gameRepo.deleteById(id);
    }
    public Game updateGame(UUID id, Game updatedGame) {
        if(updatedGame != null && (updatedGame.getTitle().isEmpty() || updatedGame.getTitle() == null
                                   || updatedGame.getPlatform().isEmpty() || updatedGame.getPlatform() == null))
        throw new InvalidGameException("Favor preecher os campos obrigatorios antes de atualizar!");
        return gameRepo.findById(id)
                .map(existingGame -> {
                    existingGame.setTitle(updatedGame.getTitle());
                    existingGame.setPlatform(updatedGame.getPlatform());
                    existingGame.setPriceRent(updatedGame.getPriceRent());
                    existingGame.setDisponible(updatedGame.isDisponible());
                    return gameRepo.save(existingGame);
                })
                .orElseThrow(() -> new GameNotFoundException("Jogo não encontrado com o ID: " + id));
    }
}
