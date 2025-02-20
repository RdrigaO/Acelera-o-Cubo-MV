package com.br.neoGenesis.api.controller;

import com.br.neoGenesis.domain.model.Game;
import com.br.neoGenesis.domain.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

//    public GameController(GameService gameService) {
//        this.gameService = gameService;
//    }

    @GetMapping
    public List<Game> listGames(){
        return gameService.listGames();
    }
    @GetMapping("/disponibles")
    public List<Game> listDisponibles(){
        return gameService.listDisponibleGames();
    }
    @PostMapping
    public Game addGame(@RequestBody Game game){
        return gameService.saveGame(game);
    }
    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable UUID id){
        gameService.removeGame(id);
    }
    @PutMapping
    public Game updateGame(@PathVariable UUID id, @RequestBody Game updatedGame){
        return gameService.updateGame(id, updatedGame);
    }
}
