package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shuffle.GameMoves;
import com.jvmless.threecardgame.domain.shuffle.GameMovesRepository;

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
