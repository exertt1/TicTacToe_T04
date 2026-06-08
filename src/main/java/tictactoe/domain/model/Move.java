package tictactoe.domain.model;

public class Move {
    public int Row;
    public int Col;
    public int Score;

    public Move() {
        this.Row = -1;
        this.Col = -1;
        this.Score = 0;
    }

    public Move(int row, int col, int score) {
        this.Row = row;
        this.Col = col;
        this.Score = score;
    }
}