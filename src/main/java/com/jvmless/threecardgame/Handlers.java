package com.jvmless.threecardgame;

import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.handlers.commands.JoinGameCommandHandler;
import com.jvmless.threecardgame.handlers.commands.MakeMoveCommandHandler;
import com.jvmless.threecardgame.handlers.commands.PlayGameCommandHandler;
import com.jvmless.threecardgame.handlers.commands.StartGameCommandHandler;
import com.jvmless.threecardgame.handlers.queries.AvailableGameQueryHandler;
import com.jvmless.threecardgame.services.GameEventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Handlers {

    @Bean
    public StartGameCommandHandler startGameCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository, GameEventService gameEventService) {
        return new StartGameCommandHandler(playerRepository, gamesRepository, gameEventService);
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

    @Bean
    public AvailableGameQueryHandler availableGameQueryHandler(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        return new AvailableGameQueryHandler(gamesRepository, playerRepository);
    }
}
