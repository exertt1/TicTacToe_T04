package tictactoe.datasource.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import tictactoe.datasource.repository.entities.Games;

import java.util.Optional;
import java.util.UUID;

public interface GamesRepository extends CrudRepository<Games, UUID> {
    @NotNull
    public Optional<Games> findById(@NotNull UUID id);

}
