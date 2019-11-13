package com.jvmless.threecardgame.services;

import com.jvmless.threecardgame.domain.game.GameId;

public interface GameEventService {
    void sendGameStartedEvent(GameId gameId);
    void sendActiveGames();
    void gameEnd(GameId gameId);
}
