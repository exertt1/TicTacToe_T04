package tictactoe.domain.model;


import tictactoe.datasource.repository.entities.Games;
import tictactoe.datasource.repository.entities.Players;
import tictactoe.domain.pkg.GameErrors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class DomainGame {
    private UUID id;
    private Board gameBoard;
    private String mode;
    private Players player;
    private Players player1;
    private Players player2;
    private byte playerMark;
    private byte computerMark;
    private byte player1Mark;
    private byte player2Mark;
    private byte currentTurn;
    private Move lastComputerMove;
    private byte status;
    private byte winner;
    private String message;
    private String err;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    public DomainGame() {
//        this.id = UUID.randomUUID();
//        this.gameBoard = new Board();
//        this.playerMark = Consts.PLAYER;
//        this.computerMark = Consts.COMPUTER;
//        this.currentTurn = Consts.PLAYER;
//        this.winner = Consts.NOBODY;
//        this.status = Consts.ACTIVE;
//        this.createdAt = LocalDateTime.now();
//    }

    public DomainGame(UUID id, Board gameBoard, byte playerMark, byte player1Mark, byte player2Mark ,byte computerMark,
                      byte currentTurn, Move lastComputerMove, byte status, byte winner,
                      String message, String err, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.gameBoard = gameBoard;
        this.playerMark = playerMark;
        this.player1Mark = player1Mark;
        this.player2Mark = player2Mark;
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
    public String getMode() { return this.mode; }
    public Board getGameBoard() { return gameBoard; }
    public byte getPlayerMark() { return playerMark; }
    public byte getPlayer1Mark() { return player1Mark; }
    public byte getPlayer2Mark() { return player2Mark; }
    public byte getComputerMark() { return computerMark; }
    public byte getCurrentTurn() { return currentTurn; }
    public Move getLastComputerMove() { return lastComputerMove; }
    public byte getStatus() { return status; }
    public byte getWinner() { return winner; }
    public String getMessage() { return message; }
    public String getErr() { return err; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Сеттеры
    public void setId(UUID id) { this.id = id; }
    public void setGameBoard(Board gameBoard) { this.gameBoard = gameBoard; }
    public void setPlayerMark(byte playerMark) { this.playerMark = playerMark; }
    public void setPlayer1Mark(byte playerMark) { this.player1Mark = playerMark; }
    public void setPlayer2Mark(byte playerMark) { this.player2Mark = playerMark; }
    public void setComputerMark(byte computerMark) { this.computerMark = computerMark; }
    public void setCurrentTurn(byte currentTurn) { this.currentTurn = currentTurn; }
    public void setLastComputerMove(Move lastComputerMove) { this.lastComputerMove = lastComputerMove; }
    public void setStatus(byte status) { this.status = status; }
    public void setWinner(byte winner) { this.winner = winner; }
    public void setMessage(String message) { this.message = message; }
    public void setErr(String err) { this.err = err; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

//    public static DomainGame initGame() {
//        DomainGame domainGame = new DomainGame();
//        domainGame.id = UUID.randomUUID();
//        domainGame.gameBoard = new Board();
//        domainGame.status = Consts.ACTIVE;
//        domainGame.winner = Consts.NOBODY;
//        domainGame.createdAt = LocalDateTime.now();
//        domainGame.updatedAt = LocalDateTime.now();
//        return domainGame;
//    }

    public byte get(int i, int j) {
        return this.gameBoard.get(i, j);
    }

    public void Move(int row, int col) throws Exception {

        if (this.isFinished()) {
            throw GameErrors.errGameFinished;
        }
        if (this.get(row, col) != Consts.EMPTY) {
            throw GameErrors.errCellOccupied;
        }

        this.playerMove(row, col);

        if (this.isFinished()) {
            if (this.checkWinner(this.playerMark))
                this.setWinner(this.playerMark);
            else if (checkWinner(this.computerMark))
                this.setWinner(this.computerMark);
            else
                this.setWinner(Consts.NOBODY);
        }

        Computer.computerMove(this);

    }

//    public void play() {
//        Scanner scanner = new Scanner(System.in);
//        Random random = new Random();
//
//        while (!isFinished()) {
//            gameBoard.draw();
//            System.out.println("Ваш ход: ");
//
//            int pos = scanner.nextInt();
//            int[] coords = new int[2];
//            try {
//                coords = findXY(pos);
//            } catch(Exception e) {
//                System.out.println(e.getMessage());
//            }
//            int row = coords[0];
//            int col = coords[1];
//
//            try {
//                this.playerMove(row, col);
//            } catch (Exception e) {
//                System.out.printf("ошибка: %s: %s%n", e.getMessage(), e.getCause());
//                continue;
//            }
//            if (isFinished()) {
//                break;
//            }
////            List<int[]> emptyCells = findEmptyCells();
////            if (emptyCells.isEmpty()) {
////                continue;
////            }
////
////            int ran = emptyCells.size() > 1 ? random.nextInt(emptyCells.size()) : 0;
////            int randY = emptyCells.get(ran)[0];
////            int randX = emptyCells.get(ran)[1];
//
//            try {
//                Computer.computerMove(this);
//            } catch (Exception e) {
//                System.out.printf("ошибка компьютера!: %s %s", e.getMessage(), e.toString());
//            }
//        }
//
//        gameBoard.draw();
//        System.out.println("игра закончена");
//        scanner.close();
//    }

    public List<int[]> findEmptyCells() {
        return gameBoard.findEmptyCells();
    }

    public void set(int row, int col, byte value) {
        this.gameBoard.set(row, col, value);
    }

    public void makeMove(int row, int col, byte player) throws Exception {
        gameBoard.makeMove(row, col, player);
    }

    public void playerMove(int row, int col) throws Exception {

        makeMove(row, col, this.playerMark);
        this.setCurrentTurn(Consts.COMPUTER);

    }

    public boolean isFinished() {
        if (gameBoard.isFinished()) {
            this.status = Consts.FINISHED;
        }
        return gameBoard.isFinished();
    }

    public boolean checkWinner(int mark) {
        return gameBoard.checkWin(mark);
    }

    public boolean isDraw() {
        return gameBoard.isDraw();
    }

    public String boardToSymbols() {
        return gameBoard.boardToSymbols();
    }

    private int[] findXY(int pos) throws Exception {
        if (pos < 1 || pos > 9) {
            throw new Exception("game.findXY: pos in [1, 9]");
        }

        for (int i = 0; i < Consts.ROWS; i++) {
            for (int j = 0; j < Consts.COLS; j++) {
                if (i * Consts.ROWS + j == pos - 1) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    public void setCure() {
    }
}