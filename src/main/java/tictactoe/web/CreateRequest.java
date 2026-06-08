package tictactoe.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateRequest {
    @JsonProperty("mode")
    private String mode;

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
