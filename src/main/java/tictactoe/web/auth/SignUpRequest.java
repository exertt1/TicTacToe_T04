package tictactoe.web.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpRequest {
    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

    public String getLogin() {
        return login;
    }
    public void setLogin(String newLogin) {
        login = newLogin;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String newPassword) {
        password = newPassword;
    }
}
