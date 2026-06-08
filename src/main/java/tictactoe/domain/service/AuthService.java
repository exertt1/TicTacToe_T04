package tictactoe.domain.service;

import tictactoe.datasource.repository.PlayersRepository;
import tictactoe.datasource.repository.entities.Players;
import tictactoe.web.auth.SignUpRequest;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

public class AuthService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public SignUpRequest decodeBasicAuth(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String base64Credentials = authHeader.substring(6);
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] parts = credentials.split(":", 2);
        return new SignUpRequest(parts[0], parts[1]);
    }

    public Players register(SignUpRequest signUpRequest) {
        if (userService.existingByLogin(signUpRequest.getLogin())) {
            throw new RuntimeException("User with login" + signUpRequest.getLogin() +  "already exists");
        }

        Players player = new Players(signUpRequest.getLogin(), signUpRequest.getPassword());
        return userService.save(player);
    }

    public UUID authorize(SignUpRequest signUpRequest) {
        Players player = userService.findPlayerByLogin(signUpRequest.getLogin());

        if (!player.getPassword().equals(signUpRequest.getPassword())) {
            throw new RuntimeException("Invalid login or password");
        }

        return player.getId();
    }

    public Optional<Players> getPlayerByID(UUID id) {
        return userService.findPlayerByID(id);
    }


}
