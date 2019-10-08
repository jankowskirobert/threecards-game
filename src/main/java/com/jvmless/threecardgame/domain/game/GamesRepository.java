package com.jvmless.threecardgame.domain.game;

import java.util.List;

public interface GamesRepository {
    Game findActiveGamesByHostId(HostId hostId);
    Game findActiveGamesByGamerId(GamerId gamerId);

    Game findByGameId(GameId gameId);
    void save(Game newGame);

    List<Game> findAllActive();
}
