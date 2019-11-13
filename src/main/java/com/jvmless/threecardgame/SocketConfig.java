package com.jvmless.threecardgame;

import com.corundumstudio.socketio.SocketIOServer;

import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.handlers.queries.AvailableGameQueryHandler;
import com.jvmless.threecardgame.services.GameEventService;
import com.jvmless.threecardgame.services.SocketIOGameEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Slf4j
public class SocketConfig {



    @Bean
    public SocketIOServer webSocketServer() {

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("localhost");
        config.setPort(9092);
//        config.setTransports(Transport.WEBSOCKET, Transport.POLLING);


        final SocketIOServer server = new SocketIOServer(config);

        return server;

    }

    @Bean
    public GameEventService gameEventService(SocketIOServer socketIOServer, AvailableGameQueryHandler availableGameQueryHandler, GamesRepository gamesRepository) {
        return new SocketIOGameEventService(socketIOServer, availableGameQueryHandler, gamesRepository);
    }


}

