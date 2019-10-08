package com.jvmless.threecardgame.api;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectToServerListener implements ConnectListener {

    private final PlayerRepository playerRepository;

    public ConnectToServerListener(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        PlayerId playerId = new PlayerId(socketIOClient.getSessionId().toString());
        log.info("Player {} connected", playerId);
        Player player = new Player(playerId);
        playerRepository.save(player);
        log.info("Currently there is {} players on server", playerRepository.countAll());

    }
}
