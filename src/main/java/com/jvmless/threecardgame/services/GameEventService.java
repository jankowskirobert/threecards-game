package com.jvmless.threecardgame.services;

import com.corundumstudio.socketio.SocketIOServer;
import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.handlers.queries.AvailableGameQueryHandler;

public class GameEventService {
    private final SocketIOServer socketIOServer;
    private final AvailableGameQueryHandler availableGameQueryHandler;
    public GameEventService(SocketIOServer socketIOServer, AvailableGameQueryHandler availableGameQueryHandler) {
        this.socketIOServer = socketIOServer;
        this.availableGameQueryHandler = availableGameQueryHandler;
    }

    public void sendGameStartedEvent(GameId gameId) {
        socketIOServer.getBroadcastOperations().sendEvent("startedGames", gameId);
    }

    public void sendActiveGames() {
        socketIOServer.getBroadcastOperations().sendEvent("activeGames", availableGameQueryHandler.query());
    }

}
