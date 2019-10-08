package com.jvmless.threecardgame;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.jvmless.threecardgame.api.StartGameListener;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.game.InMemoryGameRepository;
import com.jvmless.threecardgame.domain.player.InMemoryPlayerRepository;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.handlers.StartGameCommand;
import com.jvmless.threecardgame.handlers.StartGameCommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Slf4j
public class SocketConfig {


    @Bean
    public GamesRepository gamesRepository() {
        return new InMemoryGameRepository();
    }

    @Bean
    public PlayerRepository playerRepository() {
        return new InMemoryPlayerRepository();
    }

    @Bean
    public StartGameCommandHandler startGameCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        return new StartGameCommandHandler(playerRepository, gamesRepository);
    }

    @Bean
    public DataListener<StartGameCommand> startGameCommandDataListener(StartGameCommandHandler startGameCommandHandler) {
        return new StartGameListener(startGameCommandHandler);
    }

    @Bean
    public SocketIOServer webSocketServer(DataListener<StartGameCommand> startGameCommandDataListener) {

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                log.info("TEST");

            }
        });

        server.addEventListener("startGame", StartGameCommand.class, startGameCommandDataListener);

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient socketIOClient) {
                log.info("Test2");
            }
        });

        return server;

    }
}

