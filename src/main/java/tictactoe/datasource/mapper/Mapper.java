package tictactoe.datasource.mapper;

import tictactoe.datasource.model.DataGame;
import tictactoe.datasource.repository.entities.Games;
import tictactoe.domain.model.Board;
import tictactoe.domain.model.DomainGame;
import tictactoe.domain.model.Move;

import javax.xml.crypto.Data;
import java.util.Optional;

public class Mapper {
    public static DomainGame toDomain(Games game) {
        Move move = new Move(game.getLastMoveRow(), game.getLastMoveCol(), game.getLastMoveScore());
        return new DomainGame(
                game.getId(),
                game.symbolsToBoard(),
                DataGame.symbolToNum(game.getPlayerMark()),
                DataGame.symbolToNum(game.getPlayer1Mark()),
                DataGame.symbolToNum(game.getPlayer2Mark()),
                DataGame.symbolToNum(game.getComputerMark()),
                DataGame.playerToNum(game.getCurrentTurn()),
                move,
                DataGame.statusToNum(game.getStatus()),
                DataGame.playerToNum(game.getWinner()),
                game.getMessage(),
                game.getErr(),
                game.getCreatedAt(),
                game.getUpdatedAt()
        );

    }

    public static Games toData(DomainGame domainGame) {
        Games games = new Games();
        games.setId(domainGame.getId());
        games.setBoard(domainGame.getGameBoard().boardToSymbols());
        games.setMode(domainGame.getMode());
        games.setStatus(Board.numToStatus(domainGame.getStatus()));
        games.setComputerMark(Board.numToSymbol(domainGame.getComputerMark()));
        games.setPlayerMark(Board.numToSymbol(domainGame.getPlayerMark()));
        games.setPlayer1Mark(Board.numToSymbol(domainGame.getPlayer1Mark()));
        games.setPlayer2Mark(Board.numToSymbol(domainGame.getPlayer2Mark()));
        games.setCurrentTurn(Board.numToPlayer(domainGame.getCurrentTurn()));
        games.setErr(domainGame.getErr());
        games.setMessage(domainGame.getMessage());
        games.setWinner(Board.numToPlayer(domainGame.getWinner()));
        games.setCreatedAt(domainGame.getCreatedAt());
        games.setUpdatedAt(domainGame.getUpdatedAt());
        return games;
    }
}
