package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shuffle.GameMoves;
import com.jvmless.threecardgame.domain.shuffle.GameMovesRepository;

import java.util.List;

public class CassandraGameMovesRepositoryAdapter implements GameMovesRepository {

    private final CassandraGameMovesRepository cassandraGameMovesRepository;

    public CassandraGameMovesRepositoryAdapter(CassandraGameMovesRepository cassandraGameMovesRepository) {
        this.cassandraGameMovesRepository = cassandraGameMovesRepository;
    }

    @Override
    public GameMoves findByGameId(GameId gameId) {
        List<CassandraGameMoves> cassandraGameMoves = cassandraGameMovesRepository.findByGameId(gameId);
        if (cassandraGameMoves != null)
            return MovesCassandraMapper.map(cassandraGameMoves);
        else
            return null;
    }

    @Override
    public void save(GameMoves gameMoves) {
        cassandraGameMovesRepository.saveAll(MovesCassandraMapper.map(gameMoves));
    }
}
