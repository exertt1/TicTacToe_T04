package tictactoe.domain.service;

import tictactoe.datasource.repository.PlayersRepository;
import tictactoe.datasource.repository.entities.Players;

import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final PlayersRepository repo;

    public UserService(PlayersRepository repo) {
        this.repo = repo;
    }

    public boolean existingByLogin(String login) {
        return repo.existingByLogin(login);
    }

    public Players save(Players player) {
        return repo.save(player);
    }

    public void registration(String login, String password) {
        Players player = new Players(login, password);
        this.repo.save(player);
    }

    public Players findPlayerByLogin(String login) {
        return this.repo.findByLogin(login).orElse(null);
    }

    public Optional<Players> findPlayerByID(UUID id) {
        return repo.findByID(id);
    }

    public Players createPlayer(String login, String password) {
        Players players = new Players(login, password);
        repo.save(players);
        return players;
    }

    public boolean authorization(String login, String password) {
        Optional<Players> playerInDB = this.repo.findByLogin(login);
        return playerInDB.map(players -> players.getPassword().equals(password)).orElse(false);
    }
}
