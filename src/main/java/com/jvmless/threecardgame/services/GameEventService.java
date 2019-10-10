package com.jvmless.threecardgame.services;

import com.corundumstudio.socketio.SocketIOServer;
import com.jvmless.threecardgame.domain.game.GameId;

public class GameEventService {
    private final SocketIOServer socketIOServer;

    public GameEventService(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

    public void sendGameStartedEvent(GameId gameId) {
        socketIOServer.getBroadcastOperations().sendEvent("startedGames", gameId);
    }

}
