package com.br.neoGenesis.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.br.neoGenesis.domain.model.Game;
import com.br.neoGenesis.domain.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @Operation(summary = "List all games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success to list all games"),
            @ApiResponse(responseCode = "500", description = "Intern error in server")
    })
    @GetMapping
    public List<Game> listGames(){
        return gameService.listGames();
    }
    @GetMapping("/disponibles")
    public List<Game> listDisponibles(){
        return gameService.listDisponibleGames();
    }
    @PostMapping
    public Game AddGame(@RequestBody Game game){
        return gameService.saveGame(game);
    }
    @DeleteMapping("/{id}")
    public void DeleteGame(@PathVariable UUID id){
        gameService.removeGame(id);
    }
    @PutMapping("/{id}")
    public Game UpdateGame(@PathVariable UUID id, @RequestBody Game updatedGame){
        return gameService.updateGame(id, updatedGame);
    }
}
