package com.jvmless.threecardgame.services;

import com.jvmless.threecardgame.domain.game.GameId;

public class DummyGameEventService implements GameEventService {

    @Override
    public void sendGameStartedEvent(GameId gameId) {

    }

    @Override
    public void sendActiveGames() {

    }

    @Override
    public void gameEnd(GameId gameId) {

    }
}
