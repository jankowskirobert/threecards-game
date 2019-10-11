package com.jvmless.threecardgame;

import com.corundumstudio.socketio.SocketIOServer;
import com.jvmless.threecardgame.services.GameEventService;
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

        final SocketIOServer server = new SocketIOServer(config);


        return server;

    }

    @Bean
    public GameEventService gameEventService(SocketIOServer socketIOServer) {
        return new GameEventService(socketIOServer);
    }


}

