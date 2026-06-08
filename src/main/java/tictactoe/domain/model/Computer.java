package tictactoe.domain.model;

import tictactoe.domain.pkg.GameErrors;

import java.time.LocalDateTime;

public class Computer {

    public static void computerMove(DomainGame g) throws Exception {
        // Проверяем, можно ли делать ход
        if (g.getStatus() != Consts.ACTIVE) {
            throw GameErrors.errGameFinished;
        }

        if (g.getCurrentTurn() != Consts.COMPUTER) {
            throw GameErrors.errNotComputerTurn;
        }

        // Получаем лучший ход
        Move bestMove = minimax(g, g.getComputerMark());

        // Если нет ходов
        if (bestMove.Row == -1 && bestMove.Col == -1) {
            throw GameErrors.errNoMovesLeft;
        }

        // Делаем ход
        g.makeMove(bestMove.Row, bestMove.Col, g.getComputerMark());
        g.setLastComputerMove(new Move(bestMove.Row, bestMove.Col, 0));
        g.setUpdatedAt(LocalDateTime.now());

        // Проверяем победу
        if (checkWin(g, g.getComputerMark())) {
            g.setWinner(Consts.COMPUTER);
            g.setStatus(Consts.FINISHED);
            return;
        }

        // Проверяем ничью
        if (isDraw(g)) {
            g.setStatus(Consts.DRAW);
            return;
        }

        // Меняем ход
        g.setCurrentTurn(Consts.PLAYER);
    }

    // minimax - классический алгоритм Minimax
    private static Move minimax(DomainGame g, int currentPlayer) throws Exception {
        Move bestMove = new Move(-1, -1, 0);

        // Базовые случаи - проверяем победу
        if (checkWin(g, g.getComputerMark())) {
            return new Move(-1, -1, 10);
        } else if (checkWin(g, g.getPlayerMark())) {
            return new Move(-1, -1, -10);
        } else if (isDraw(g)) {
            return new Move(-1, -1, 0);
        }

        // Если ход компьютера
        if (currentPlayer == g.getComputerMark()) {
            bestMove.Score = Integer.MIN_VALUE;

            // Перебираем все пустые клетки
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (g.get(i, j) == Consts.EMPTY) {
                        // Пробуем ход
                        g.set(i, j, g.getComputerMark());

                        // Рекурсивно вызываем minimax для игрока
                        Move move = minimax(g, g.getPlayerMark());

                        // Откатываем ход
                        g.set(i, j, Consts.EMPTY);

                        // Выбираем лучший ход
                        if (move.Score > bestMove.Score) {
                            bestMove.Score = move.Score;
                            bestMove.Row = i;
                            bestMove.Col = j;
                        }
                    }
                }
            }
        } else {
            // Если ход игрока
            bestMove.Score = Integer.MAX_VALUE;

            // Перебираем все пустые клетки
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (g.get(i, j) == Consts.EMPTY) {
                        // Пробуем ход игрока
                        g.set(i, j, g.getPlayerMark());

                        // Рекурсивно вызываем minimax для компьютера
                        Move move = minimax(g, g.getComputerMark());

                        // Откатываем ход
                        g.set(i, j, Consts.EMPTY);

                        // Выбираем ход с минимальным счетом
                        if (move.Score < bestMove.Score) {
                            bestMove.Score = move.Score;
                            bestMove.Row = i;
                            bestMove.Col = j;
                        }
                    }
                }
            }
        }

        return bestMove;
    }

    // checkWin - проверяет победу для заданной метки
    private static boolean checkWin(DomainGame g, int mark) {
        return g.checkWinner(mark);
    }

    // isDraw - проверяет ничью
    private static boolean isDraw(DomainGame g) {
        return g.isDraw();
    }
}