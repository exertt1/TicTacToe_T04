package tictactoe.web;


import org.springframework.web.bind.annotation.*;
import tictactoe.datasource.mapper.Mapper;
import tictactoe.datasource.model.DataGame;
import tictactoe.domain.model.DomainGame;
import tictactoe.domain.service.GameService;

import java.net.HttpURLConnection;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {
    public GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public ApiResponse<DataGame> createGame(@RequestBody ) {
        DomainGame domainGame;
        try {
            domainGame = gameService.createGame();
        } catch(Exception e) {
            return ApiResponse.error(e.getMessage(), HttpURLConnection.HTTP_BAD_REQUEST);
        }
        return ApiResponse.success(Mapper.toData(domainGame));
    }

    @GetMapping("/{id}")
    public ApiResponse<DataGame> getGame(@PathVariable UUID id) {
        DomainGame domainGame;
        try {
            domainGame = gameService.getGame(id);
        } catch(Exception e) {
            return ApiResponse.error(e.getMessage(), HttpURLConnection.HTTP_NOT_FOUND);
        }
        return ApiResponse.success(Mapper.toData(domainGame));
    }

    @PostMapping("/{id}/move")
    public ApiResponse<DataGame> move(@PathVariable UUID id, @RequestBody MoveRequest request) {
        DomainGame domainGame;
        try {
            domainGame = gameService.getGame(id);
        } catch(Exception e) {
            return ApiResponse.error(e.getMessage(), HttpURLConnection.HTTP_NOT_FOUND);
        }
        try {
            domainGame.Move(request.getRow(), request.getCol());

        } catch (Exception e) {
            return ApiResponse.error(e.getMessage(), HttpURLConnection.HTTP_BAD_REQUEST);
        }
        try {
            gameService.updateGame(domainGame);
        } catch (Exception err) {
            return ApiResponse.error(err.getMessage(), HttpURLConnection.HTTP_NOT_FOUND);
        }
        return ApiResponse.success(Mapper.toData(domainGame));
    }
}
