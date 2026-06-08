package tictactoe.domain.service;

import tictactoe.datasource.repository.*;
import tictactoe.datasource.repository.entities.Games;
import tictactoe.datasource.repository.entities.Players;

import java.util.Optional;
import java.util.UUID;

public class GameService {
    private final PlayersRepository playersRepository;
    private final GamesRepository gamesRepository;

    public GameService(PlayersRepository playersRepository, GamesRepository gamesRepository) {
        this.playersRepository = playersRepository;
        this.gamesRepository = gamesRepository;
    }

    public Players createPlayer(String login, String password) {
        Players players = new Players(login, password);
        playersRepository.save(players);
        return players;
    }

    public Players findPlayerByLogin(String login) {
        return this.playersRepository.findByLogin(login).orElse(null);
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
