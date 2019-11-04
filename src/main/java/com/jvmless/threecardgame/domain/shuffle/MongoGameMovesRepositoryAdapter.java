package com.jvmless.threecardgame.domain.shuffle;

import com.jvmless.threecardgame.domain.game.GameId;

public class MongoGameMovesRepositoryAdapter implements GameMovesRepository {

    private final MongoGameMovesRepository mongoGameMovesRepository;

    public MongoGameMovesRepositoryAdapter(MongoGameMovesRepository mongoGameMovesRepository) {
        this.mongoGameMovesRepository = mongoGameMovesRepository;
    }

    @Override
    public GameMoves findByGameId(GameId gameId) {
        return mongoGameMovesRepository.findByGameId(gameId);
    }

    @Override
    public void save(GameMoves gameMoves) {
        mongoGameMovesRepository.save(gameMoves);
    }
}
