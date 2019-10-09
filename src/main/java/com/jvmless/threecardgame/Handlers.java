package com.jvmless.threecardgame;

import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.handlers.JoinGameCommandHandler;
import com.jvmless.threecardgame.handlers.MakeMoveCommandHandler;
import com.jvmless.threecardgame.handlers.PlayGameCommandHandler;
import com.jvmless.threecardgame.handlers.StartGameCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Handlers {

    @Bean
    public StartGameCommandHandler startGameCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        return new StartGameCommandHandler(playerRepository, gamesRepository);
    }

    @Bean
    public PlayGameCommandHandler playGameCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        return new PlayGameCommandHandler(gamesRepository, playerRepository);
    }

    @Bean
    public MakeMoveCommandHandler makeMoveCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        return new MakeMoveCommandHandler(gamesRepository, playerRepository);
    }

    @Bean
    public JoinGameCommandHandler joinGameCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        return new JoinGameCommandHandler(gamesRepository, playerRepository);
    }
}
