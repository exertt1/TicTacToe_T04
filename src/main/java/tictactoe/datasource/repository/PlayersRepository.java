package tictactoe.datasource.repository;

import org.springframework.data.repository.CrudRepository;
import tictactoe.datasource.repository.entities.Players;

import java.util.Optional;
import java.util.UUID;

public interface PlayersRepository extends CrudRepository<Players, UUID> {
    Optional<Players> findByLogin(String Login);

    Optional<Players> findByID(UUID id);

    boolean existingByLogin(String login);


}
