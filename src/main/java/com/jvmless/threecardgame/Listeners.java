package com.jvmless.threecardgame;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.jvmless.threecardgame.api.*;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.handlers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Listeners {

    @Autowired
    SocketIOServer socketIOServer;

    @Bean
    public DisconnectListener disconnectListener(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        DisconnectFromServerListener disconnectFromServerListener = new DisconnectFromServerListener(playerRepository, gamesRepository);
        socketIOServer.addDisconnectListener(disconnectFromServerListener);
        return disconnectFromServerListener;
    }

    @Bean
    public ConnectListener connectListener(PlayerRepository playerRepository) {
        ConnectToServerListener connectToServerListener = new ConnectToServerListener(playerRepository);
        socketIOServer.addConnectListener(connectToServerListener);
        return connectToServerListener;
    }

    @Bean
    public DataListener<StartGameCommand> startGameCommandDataListener(StartGameCommandHandler startGameCommandHandler) {
        StartGameListener startGameListener = new StartGameListener(startGameCommandHandler);
        socketIOServer.addEventListener("startGame", StartGameCommand.class, startGameListener);
        return startGameListener;
    }

    @Bean
    public DataListener<JoinGameCommand> joinGameCommandDataListener(JoinGameCommandHandler joinGameCommandHandler) {
        JoinGameListener joinGameListener = new JoinGameListener(joinGameCommandHandler);
        socketIOServer.addEventListener("joinGame", JoinGameCommand.class, joinGameListener);
        return joinGameListener;
    }

    @Bean
    public DataListener<PlayGameCommand> playGameCommandDataListener(PlayGameCommandHandler playGameCommandHandler) {
        PlayGameListener playGameListener = new PlayGameListener(playGameCommandHandler);
        socketIOServer.addEventListener("playGame", PlayGameCommand.class, playGameListener);
        return playGameListener;
    }

    @Bean
    public DataListener<MakeMoveCommand> makeMoveCommandDataListener(MakeMoveCommandHandler makeMoveCommandHandler) {
        MakeMoveListener makeMoveListener = new MakeMoveListener(makeMoveCommandHandler);
        socketIOServer.addEventListener("makeMove", MakeMoveCommand.class, makeMoveListener);
        return makeMoveListener;
    }

}
