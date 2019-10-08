package com.jvmless.threecardgame.domain.player;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPlayerRepository implements PlayerRepository {

    private ConcurrentHashMap<PlayerId, Player> repo = new ConcurrentHashMap<>();

    @Override
    public void save(Player player) {
        repo.put(player.getPlayerId(), player);
    }

    @Override
    public long countAll() {
        return repo.size();
    }

    @Override
    public Player find(PlayerId playerId) {
        return repo.entrySet().stream().map(x -> x.getValue()).filter(y -> y.getPlayerId().equals(playerId)).findFirst().orElse(null);
    }
}
