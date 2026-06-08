package tictactoe.domain.pkg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppError extends Exception {
    @JsonProperty("code")
    public String code;

    @JsonProperty("message")
    public String message;

    @JsonProperty("status")
    public int status;

    @JsonProperty("-")
    public Error err;

    AppError(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String error() {
        if (this.err != null) {
            return String.format(
                    "%d: %s - %s",
                    code, message, err.toString()
            );
        }
        return String.format(
                "%d: %s",
                code, message
        );
    }
}

