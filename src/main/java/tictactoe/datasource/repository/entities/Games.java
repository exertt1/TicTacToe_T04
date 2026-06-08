package tictactoe.datasource.repository.entities;

import jakarta.persistence.*;
import tictactoe.domain.model.Board;
import tictactoe.domain.model.Consts;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "games")
public class Games {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private String mode;
    private String board;
    private String currentTurn;
    private String winner;
    private String status;
    private String message;
    private String err;
    private char playerMark;
    private char computerMark;
    private char player1Mark;
    private char player2Mark;
    private byte lastMoveRow;
    private byte lastMoveCol;
    private byte lastMoveScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Players players;

    @ManyToOne
    @JoinColumn(name = "player1_id")
    private Players players1;

    @ManyToOne
    @JoinColumn(name = "player2_id")
    private Players players2;

    public Games() {}

    public Games(String mode, Players players) {
        this.mode = mode;
        if (mode.equals("VSPlayer")) {
            this.status = "Waiting";
            if (Math.random() % 2 == 0) {
                this.playerMark = Board.numToSymbol(Consts.CROSS);
                this.player1Mark = playerMark;
                this.player2Mark = Board.numToSymbol(Consts.ZERO);
            } else {
                this.playerMark = Board.numToSymbol(Consts.ZERO);
                this.player1Mark = playerMark;
                this.player2Mark = Board.numToSymbol(Consts.CROSS);
            }
        }
        else {
            this.status = "Active";
            this.playerMark = Board.numToSymbol(Consts.CROSS);
            this.player1Mark = playerMark;
            this.computerMark = Board.numToSymbol(Consts.ZERO);
        }
        this.players = players;
        this.players1 = players;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    public String getBoard() { return board; }
    public void setBoard(String board) { this.board = board; }

    public String getCurrentTurn() { return currentTurn; }
    public void setCurrentTurn(String currentTurn) { this.currentTurn = currentTurn; }

    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getErr() { return err; }
    public void setErr(String err) { this.err = err; }

    public Players getPlayer() { return players; }
    public void setPlayer(Players players) { this.players = players; }

    public Players getPlayer1() { return this.players1; }
    public void setPlayer1(Players players1) { this.players1 = players1; }

    public Players getPlayer2() { return this.players2; }
    public void setPlayer2(Players players2) { this.players2 = players2; }

    public char getPlayerMark() { return playerMark; }
    public void setPlayerMark(char mark) { this.playerMark = mark; }

    public char getPlayer1Mark() { return this.playerMark; }
    public void setPlayer1Mark(char mark) { this.player1Mark = mark; }

    public char getPlayer2Mark() { return this.player2Mark; }
    public void setPlayer2Mark(char mark) { this.player2Mark = mark; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public char getComputerMark() {
        return this.computerMark;
    }
    public void setComputerMark(char mark) {
        this.computerMark = mark;
    }
    public byte getLastMoveRow() {
        return this.lastMoveRow;
    }
    public byte getLastMoveCol() {
        return this.lastMoveCol;
    }
    public byte getLastMoveScore() {
        return this.lastMoveScore;
    }
    public void setLastMoveRow(byte row) {
        this.lastMoveRow = row;
    }
    public void setLastMoveCol(byte col) {
        this.lastMoveCol = col;
    }
    public void setLastMoveScore(byte score) {
        this.lastMoveScore = score;
    }


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

    public Board symbolsToBoard() {
        byte[][] b = new byte[3][3];
        for (int i = 0; i < 9; i++) {
            b[i/3][i%3] = Games.symbolToNum(this.board.charAt(i));
        }
        return new Board(b);
    }

}


