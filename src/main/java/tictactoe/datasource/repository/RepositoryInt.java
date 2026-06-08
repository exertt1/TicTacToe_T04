package tictactoe.datasource.repository;

import tictactoe.domain.model.DomainGame;

import java.util.UUID;

public interface RepositoryInt {
    public void save(DomainGame domainGame) throws Exception;
    public void update(DomainGame domainGame) throws Exception;
    public DomainGame get(UUID id) throws Exception;
}
