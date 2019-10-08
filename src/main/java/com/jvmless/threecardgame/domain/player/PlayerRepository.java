package com.jvmless.threecardgame.domain.player;

public interface PlayerRepository {
    Player find(PlayerId playerId);
    void save(Player player);
}
