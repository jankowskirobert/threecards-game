package com.jvmless.threecardgame;

import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.jvmless.threecardgame.api.*;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.handlers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Listeners {


    @Bean
    public DisconnectListener disconnectListener(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        return new DisconnectFromServerListener(playerRepository, gamesRepository);
    }

    @Bean
    public ConnectListener connectListener(PlayerRepository playerRepository) {
        return new ConnectToServerListener(playerRepository);
    }

    @Bean
    public DataListener<StartGameCommand> startGameCommandDataListener(StartGameCommandHandler startGameCommandHandler) {
        return new StartGameListener(startGameCommandHandler);
    }

    @Bean
    public DataListener<JoinGameCommand> joinGameCommandDataListener(JoinGameCommandHandler joinGameCommandHandler) {
        return new JoinGameListener(joinGameCommandHandler);
    }

    @Bean
    public DataListener<PlayGameCommand> playGameCommandDataListener(PlayGameCommandHandler playGameCommandHandler) {
        return new PlayGameListener(playGameCommandHandler);
    }

    @Bean
    public DataListener<MakeMoveCommand> makeMoveCommandDataListener(MakeMoveCommandHandler makeMoveCommandHandler) {
        return new MakeMoveListener(makeMoveCommandHandler);
    }

}
