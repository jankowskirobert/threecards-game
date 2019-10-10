package com.jvmless.threecardgame;

import com.corundumstudio.socketio.SocketIOServer;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.engine.GameEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EngineConfiguration {

    @Bean
    public GameEngine gameEngine(GamesRepository gamesRepository, SocketIOServer socketIOServer) {
        return new GameEngine(gamesRepository, socketIOServer);
    }

}
