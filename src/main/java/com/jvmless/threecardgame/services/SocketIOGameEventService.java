package com.jvmless.threecardgame.services;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.jvmless.threecardgame.domain.game.Game;
import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.handlers.queries.AvailableGameQueryHandler;

import java.util.UUID;

public class SocketIOGameEventService implements GameEventService {
    private final SocketIOServer socketIOServer;
    private final AvailableGameQueryHandler availableGameQueryHandler;
    private final GamesRepository gamesRepository;

    public SocketIOGameEventService(SocketIOServer socketIOServer, AvailableGameQueryHandler availableGameQueryHandler, GamesRepository gamesRepository) {
        this.socketIOServer = socketIOServer;
        this.availableGameQueryHandler = availableGameQueryHandler;
        this.gamesRepository = gamesRepository;
    }

    @Override
    public void sendGameStartedEvent(GameId gameId) {
        socketIOServer.getBroadcastOperations().sendEvent("startedGames", gameId);
    }

    @Override
    public void sendActiveGames() {
        socketIOServer.getBroadcastOperations().sendEvent("activeGames", availableGameQueryHandler.query());
    }

    @Override
    public void gameEnd(GameId gameId) {
        Game g = gamesRepository.findByGameId(gameId);
        SocketIOClient client = socketIOServer.getClient(UUID.fromString(g.getHost().getHostId().getId()));
        client.sendEvent("endGame", "END");
    }
}
