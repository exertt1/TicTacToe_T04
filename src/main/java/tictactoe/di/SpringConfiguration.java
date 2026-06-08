package tictactoe.di;

import org.springframework.context.annotation.Bean;
import tictactoe.datasource.repository.GamesRepository;
import tictactoe.datasource.repository.PlayersRepository;
import tictactoe.domain.service.GameService;
import org.springframework.context.annotation.Configuration;
import tictactoe.domain.service.UserService;

@Configuration
public class SpringConfiguration {

    @Bean
    public GameService getGameService(GamesRepository gamesRepository) {
        return new GameService(gamesRepository);
    }

    @Bean
    public UserService getUserService(PlayersRepository playersRepository) {
        return new UserService(playersRepository);
    }
}
