package tictactoe.datasource.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import tictactoe.domain.model.Board;
import tictactoe.domain.model.Consts;
import tictactoe.domain.model.DomainGame;
import tictactoe.domain.model.Move;

import java.time.LocalDateTime;
import java.util.UUID;

public class DataGame {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("board")
    private String[][] gameBoard;

    @JsonProperty("player_mark")
    private String playerMark;

    @JsonProperty("computer_mark")
    private String computerMark;

    @JsonProperty("current_turn")
    private String currentTurn;

    @JsonProperty("last_computer_move")
    private Move lastComputerMove;

    @JsonProperty("status")
    private String status;

    @JsonProperty("winner")
    private String winner;

    @JsonProperty("msg")
    private String message;

    @JsonProperty("err")
    private String err;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    // Конструкторы
    public DataGame() {}

    public DataGame(UUID id, String[][] gameBoard, String playerMark, String computerMark,
                    String currentTurn, Move lastComputerMove, String status, String winner,
                    String message, String err, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.gameBoard = gameBoard;
        this.playerMark = playerMark;
        this.computerMark = computerMark;
        this.currentTurn = currentTurn;
        this.lastComputerMove = lastComputerMove;
        this.status = status;
        this.winner = winner;
        this.message = message;
        this.err = err;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Геттеры
    public UUID getId() { return id; }
    public String[][] getGameBoard() { return gameBoard; }
    public String getPlayerMark() { return playerMark; }
    public String getComputerMark() { return computerMark; }
    public String getCurrentTurn() { return currentTurn; }
    public Move getLastComputerMove() { return lastComputerMove; }
    public String getStatus() { return status; }
    public String getWinner() { return winner; }
    public String getMessage() { return message; }
    public String getErr() { return err; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Сеттеры
    public void setId(UUID id) { this.id = id; }
    public void setGameBoard(String[][] gameBoard) { this.gameBoard = gameBoard; }
    public void setPlayerMark(String playerMark) { this.playerMark = playerMark; }
    public void setComputerMark(String computerMark) { this.computerMark = computerMark; }
    public void setCurrentTurn(String currentTurn) { this.currentTurn = currentTurn; }
    public void setLastComputerMove(Move lastComputerMove) { this.lastComputerMove = lastComputerMove; }
    public void setStatus(String status) { this.status = status; }
    public void setWinner(String winner) { this.winner = winner; }
    public void setMessage(String message) { this.message = message; }
    public void setErr(String err) { this.err = err; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }


    // Преобразование числа в символ (статический вариант, аналог NumToSymbol)
    public static byte symbolToNum(char mark) {
        return switch (mark) {
            case 'O' -> Consts.ZERO;
            case 'X' -> Consts.CROSS;
            default -> Consts.EMPTY;
        };
    }

    public static byte playerToNum(String player) {
        return switch (player) {
            case "Player" -> Consts.PLAYER ;
            case "Player1" -> Consts.PLAYER1;
            case "Player2" -> Consts.PLAYER2;
            case "Computer" -> Consts.COMPUTER;
            default -> Consts.NOBODY;
        };
    }

    public static byte statusToNum(String status) {
        return switch(status) {
            case "Finished" -> Consts.FINISHED;
            case "Active" -> Consts.ACTIVE;
            case "Waiting" -> Consts.WAITING;
            default -> Consts.DRAW;
        };
    }
}