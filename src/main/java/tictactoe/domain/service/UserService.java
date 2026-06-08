package tictactoe.domain.service;

import tictactoe.datasource.repository.PlayersRepository;
import tictactoe.datasource.repository.entities.Players;

import java.util.Optional;

public class UserService {
    private final PlayersRepository repo;

    public UserService(PlayersRepository repo) {
        this.repo = repo;
    }

    public void registration(String login, String password) {
        Players player = new Players(login, password);
        this.repo.save(player);
    }

    public void authorization(String login, String password) {
        Optional<Players> playerInDB = this.repo.findByLogin(login);

    }
}
