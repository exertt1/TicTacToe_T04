package tictactoe.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveRequest {
    @JsonProperty("row")
    private int row;

    @JsonProperty("col")
    private int col;

    public int getRow() {
        return this.row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }
    public void setCol(int col) {
        this.col = col;
    }
}
