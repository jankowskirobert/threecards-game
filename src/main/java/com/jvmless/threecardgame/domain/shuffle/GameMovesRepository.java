package com.jvmless.threecardgame.domain.shuffle;

import com.jvmless.threecardgame.domain.game.GameId;

public interface GameMovesRepository {
    GameMoves findByGameId(GameId gameId);

    void save(GameMoves gameMoves);
}
