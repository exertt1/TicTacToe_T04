package tictactoe.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("data")
    private T data;

    @JsonProperty("error")
    private String error;

    @JsonProperty("status")
    private int status;

    @JsonProperty("timestamp")
    private long timestamp;

    public ApiResponse(boolean success, T data, String error, int status) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.status = status;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(true, data, null, 200);
    }

    public static <T> ApiResponse<T> error(String message, int status) {
        return new ApiResponse<>(false, null, message, status);
    }
}

