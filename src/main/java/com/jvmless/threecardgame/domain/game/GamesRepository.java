package com.jvmless.threecardgame.domain.game;

import java.util.List;

public interface GamesRepository {
    List<Game> findActiveGamesByHostId(HostId hostId);

    void save(Game newGame);

    List<Game> findAllActive();
}
