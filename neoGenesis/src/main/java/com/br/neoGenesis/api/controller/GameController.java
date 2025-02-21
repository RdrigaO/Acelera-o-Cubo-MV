package com.br.neoGenesis.api.controller;

import com.br.neoGenesis.domain.model.Game;
import com.br.neoGenesis.domain.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("/listgames")
    public List<Game> listGames(){
        return gameService.ListGames();
    }
    @GetMapping("/disponibles")
    public List<Game> listDisponibles(){
        return gameService.ListDisponibleGames();
    }
    @PostMapping("/addgame")
    public Game AddGame(@RequestBody Game game){
        return gameService.SaveGame(game);
    }
    @DeleteMapping("/removegame/{id}")
    public void DeleteGame(@PathVariable UUID id){
        gameService.RemoveGame(id);
    }
    @PutMapping("/updategame/{id}")
    public Game UpdateGame(@PathVariable UUID id, @RequestBody Game updatedGame){
        return gameService.UpdateGame(id, updatedGame);
    }
}
