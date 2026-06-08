package tictactoe.di;

import org.springframework.context.annotation.Bean;
import tictactoe.datasource.repository.GamesRepository;
import tictactoe.datasource.repository.PlayersRepository;
import tictactoe.domain.service.GameService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public GameService getGameService(PlayersRepository playersRepository, GamesRepository gamesRepository) {
        return new GameService(playersRepository, gamesRepository);
    }
}
