package com.jvmless.threecardgame;

import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.engine.GameEngine;
import com.jvmless.threecardgame.services.GameEventService;
import com.jvmless.threecardgame.services.SocketIOGameEventService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EngineConfiguration {

    @Bean
    public GameEngine gameEngine(GamesRepository gamesRepository, GameEventService gameEventService) {
        return new GameEngine(gamesRepository, gameEventService);
    }

}
