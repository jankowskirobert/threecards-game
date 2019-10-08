package com.jvmless.threecardgame.api;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.jvmless.threecardgame.domain.game.Game;
import com.jvmless.threecardgame.domain.game.GamerId;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DisconnectFromServerListener implements DisconnectListener {

    private final PlayerRepository playerRepository;
    private final GamesRepository gamesRepository;

    public DisconnectFromServerListener(PlayerRepository playerRepository, GamesRepository gamesRepository) {
        this.playerRepository = playerRepository;
        this.gamesRepository = gamesRepository;
    }

    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        PlayerId playerId = new PlayerId(socketIOClient.getSessionId().toString());
        log.info("Player {} disconnected", playerId);
        Player player = playerRepository.find(playerId);
        if (player != null) {
            GamerId gamerId = new GamerId(socketIOClient.getSessionId().toString());
            Game game = gamesRepository.findActiveGamesByGamerId(gamerId);
            if (game != null) {
                log.info("Player {} left match {}", playerId, game.getGameId());
                game.leaveMatch(gamerId);
            }
        }
    }
}
