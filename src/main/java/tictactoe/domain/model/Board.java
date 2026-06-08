package tictactoe.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private byte[][] board;

    public Board() {
        this.board = new byte[Consts.ROWS][Consts.COLS];
    }

    public Board(byte[][] board) {
        this.board = board;
    }

    public byte[][] getBoard() {
        return this.board;
    }

    // Получение значения по координатам
    public byte get(int row, int col) {
        return board[row][col];
    }

    // Установка значения
    public void set(int row, int col, byte value) {
        board[row][col] = value;
    }

    // Проверка, пуста ли клетка
    public boolean isEmpty(int row, int col) {
        return board[row][col] == Consts.EMPTY;
    }

    // Отрисовка доски (аналог Draw())
//    public void draw() {
//        for (int i = 0; i < Consts.ROWS; i++) {
//            for (int j = 0; j < Consts.COLS; j++) {
//                String symbol = Board.numToSymbol(this.board[i][j]);
//                System.out.printf(" %s", symbol);
//                if (j != Consts.COLS - 1) {
//                    System.out.print(" |");
//                }
//            }
//            System.out.println();
//            if (i != Consts.ROWS - 1) {
//                System.out.println("---+---+---");
//            }
//        }
//    }

    // Преобразование числа в символ
//    private char numToSymbol(int value) {
//        switch (value) {
//            case Consts.CROSS: return 'X';
//            case Consts.ZERO: return 'O';
//            default: return ' ';
//        }
//    }

    // Сумма элементов массива (аналог Sum)
    public static int sum(int[] slice) {
        int result = 0;
        for (int i = 0; i < 3 && i < slice.length; i++) {
            result += slice[i];
        }
        return result;
    }

    // Поиск пустых клеток (аналог FindEmptyCells)
    public List<int[]> findEmptyCells() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < Consts.ROWS; i++) {
            for (int j = 0; j < Consts.COLS; j++) {
                if (board[i][j] == Consts.EMPTY) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        return emptyCells;
    }

    // Сделать ход (аналог MakeMove)
    public void makeMove(int row, int col, byte player) throws Exception {
        if (board[row][col] != Consts.EMPTY) {
            throw new Exception("domain.MakeMove: cell already occupied");
        }
        if (player != Consts.CROSS && player != Consts.ZERO) {
            throw new Exception("domain.MakeMove: invalid player");
        }
        board[row][col] = player;
    }

    // Проверка, закончена ли игра (аналог IsFinished)
    public boolean isFinished() {
        return checkWin(Consts.CROSS) || checkWin(Consts.ZERO) || isBoardFull();
    }

    // Проверка победы (аналог CheckWin)
    public boolean checkWin(int mark) {
        // Проверка строк
        for (int i = 0; i < Consts.ROWS; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) {
                return true;
            }
        }
        // Проверка столбцов
        for (int i = 0; i < Consts.COLS; i++) {
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) {
                return true;
            }
        }
        // Проверка диагоналей
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
            return true;
        }
        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
            return true;
        }
        return false;
    }

    // Получить доступные ходы (аналог GetAvailableMoves)
    public List<int[]> getAvailableMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < Consts.ROWS; i++) {
            for (int j = 0; j < Consts.COLS; j++) {
                if (board[i][j] == Consts.EMPTY) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }

    // Заполнена ли доска (аналог IsFull)
    public boolean isFull() {
        for (int i = 0; i < Consts.ROWS; i++) {
            for (int j = 0; j < Consts.COLS; j++) {
                if (board[i][j] == Consts.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Копирование доски (аналог Copy)
    public Board copy() {
        Board newBoard = new Board();
        for (int i = 0; i < Consts.ROWS; i++) {
            System.arraycopy(board[i], 0, newBoard.board[i], 0, Consts.COLS);
        }
        return newBoard;
    }

    // Проверка, пуста ли доска (аналог IsEmpty)
    public boolean isEmpty() {
        for (int i = 0; i < Consts.ROWS; i++) {
            for (int j = 0; j < Consts.COLS; j++) {
                if (board[i][j] != Consts.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Заполнена ли доска (аналог BoardFiled)
    public boolean isBoardFull() {
        for (int i = 0; i < Consts.ROWS; i++) {
            for (int j = 0; j < Consts.COLS; j++) {
                if (board[i][j] == Consts.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Ничья? (аналог IsDraw)
    public boolean isDraw() {
        return !checkWin(Consts.ZERO) && !checkWin(Consts.CROSS) && isFull();
    }

    // Преобразование доски в строковые символы (аналог BoardToSymbols)
    public String boardToSymbols() {
        StringBuilder symbols = new StringBuilder();
        for (int i = 0; i < Consts.ROWS; i++) {
            for (int j = 0; j < Consts.COLS; j++) {
                symbols.append(numToSymbol(board[i][j]));
            }
        }
        return symbols.toString();
    }

    // Преобразование числа в символ (статический вариант, аналог NumToSymbol)
    public static char numToSymbol(int num) {
        return switch (num) {
            case Consts.ZERO -> 'O';
            case Consts.CROSS -> 'X';
            case Consts.EMPTY -> ' ';
            default -> '\n';
        };
    }

    public static String numToPlayer(byte num) {
        return switch (num) {
            case Consts.PLAYER -> "Player";
            case Consts.PLAYER1 -> "Player1";
            case Consts.PLAYER2 -> "Player2";
            case Consts.COMPUTER -> "Computer";
            case Consts.NOBODY -> "Nobody";
            default -> "";
        };
    }

    public static String numToStatus(byte num) {
        return switch(num) {
            case Consts.FINISHED -> "Finished";
            case Consts.ACTIVE -> "Active";
            case Consts.DRAW -> "Draw";
            case Consts.WAITING -> "Waiting";
            default -> "";
        };
    }
    public static byte statusToNum (String status) {
        return switch(status) {
            case "Finished" -> Consts.FINISHED;
            case "Active" -> Consts.ACTIVE;
            case "Draw" -> Consts.DRAW;
            case "Waiting" -> Consts.WAITING;
            default -> -1;
        };
    }


}