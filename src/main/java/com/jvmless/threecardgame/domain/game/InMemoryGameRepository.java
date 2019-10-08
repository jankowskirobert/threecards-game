package com.jvmless.threecardgame.domain.game;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryGameRepository implements GamesRepository {

    private ConcurrentHashMap<GameId, Game> repo = new ConcurrentHashMap<>();

    @Override
    public Game findActiveGamesByHostId(HostId hostId) {
        Optional<Game> g = repo.entrySet().stream()
                .filter(x -> x.getValue().isActive())
                .filter(y -> y.getValue().getHost().getHostId().equals(hostId))
                .findFirst()
                .map(Map.Entry::getValue);
        return g.orElse(null);
    }

    @Override
    public Game findActiveGamesByGamerId(GamerId gamerId) {
        Optional<Game> g = repo.entrySet().stream()
                .filter(x -> x.getValue().isActive())
                .filter(y -> y.getValue().hasGamer(gamerId))
                .findFirst()
                .map(Map.Entry::getValue);
        return g.orElse(null);
    }

    @Override
    public Game findByGameId(GameId gameId) {
        Optional<Game> g = repo.entrySet().stream()
                .filter(x -> x.getValue().isActive())
                .filter(y -> y.getValue().getGameId().equals(gameId))
                .findFirst()
                .map(Map.Entry::getValue);
        return g.orElse(null);
    }

    @Override
    public void save(Game newGame) {
        repo.put(newGame.getGameId(), newGame);
    }

    @Override
    public List<Game> findAllActive() {
        return repo.entrySet().stream()
                .filter(x -> x.getValue().isActive()).map(Map.Entry::getValue).collect(Collectors.toList());
    }
}
