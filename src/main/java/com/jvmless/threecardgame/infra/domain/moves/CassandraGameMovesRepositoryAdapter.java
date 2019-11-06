package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shuffle.GameMoves;
import com.jvmless.threecardgame.domain.shuffle.GameMovesRepository;

public class CassandraGameMovesRepositoryAdapter implements GameMovesRepository {

    private final CassandraGameMovesRepository cassandraGameMovesRepository;

    public CassandraGameMovesRepositoryAdapter(CassandraGameMovesRepository cassandraGameMovesRepository) {
        this.cassandraGameMovesRepository = cassandraGameMovesRepository;
    }

    @Override
    public GameMoves findByGameId(GameId gameId) {
        CassandraGameMoves cassandraGameMoves = cassandraGameMovesRepository.findByGameId(gameId).orElse(null);
        if (cassandraGameMoves != null)
            return MovesCassandraMapper.map(cassandraGameMoves);
        else
            return null;
    }

    @Override
    public void save(GameMoves gameMoves) {
        cassandraGameMovesRepository.save(MovesCassandraMapper.map(gameMoves));
    }
}
