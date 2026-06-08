package tictactoe.domain.service;

import tictactoe.datasource.repository.*;
import tictactoe.datasource.repository.entities.Games;
import tictactoe.datasource.repository.entities.Players;

import java.util.Optional;
import java.util.UUID;

public class GameService {
    private final GamesRepository gamesRepository;

    public GameService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public Optional<Games> findGameByID(UUID id) {
        return gamesRepository.findById(id);
    }

    public Games createGame(String mode, Players players) {
        Games Games = new Games(mode, players);
        gamesRepository.save(Games);
        return Games;
    }

    public Games joinGame(Players players, UUID gameID) {
        Optional<Games> Games = gamesRepository.findById(gameID);

    }

}
