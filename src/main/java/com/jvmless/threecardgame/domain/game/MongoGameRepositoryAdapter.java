package com.jvmless.threecardgame.domain.game;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MongoGameRepositoryAdapter implements GamesRepository {

    private final MongoGameRepository mongoGameRepository;

    public MongoGameRepositoryAdapter(MongoGameRepository mongoGameRepository) {
        this.mongoGameRepository = mongoGameRepository;
    }

    @Override
    public Game findActiveGamesByHostId(HostId hostId) {
        return mongoGameRepository.findAllByHostIs(new Host(hostId))
                .stream()
                .filter(Game::isActive)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Game findActiveGamesByGamerId(GamerId gamerId) {
        return mongoGameRepository.findAllByPlayersContains(new Gamer(gamerId))
                .stream()
                .filter(Game::isActive)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Game findByGameId(GameId gameId) {
        return mongoGameRepository.findByGameId(gameId);
    }

    @Override
    public void save(Game newGame) {
        try {mongoGameRepository.save(newGame);} catch (Exception ex) {
    log.error("CANNOT SAVE GAME!",ex);
        }
    }

    @Override
    public List<Game> findAllActive() {
        return mongoGameRepository.findAll().stream().filter(Game::isActive).collect(Collectors.toList());
    }
}
