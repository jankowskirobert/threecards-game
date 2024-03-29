package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shuffle.GameMoves;
import com.jvmless.threecardgame.domain.shuffle.GameMovesRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CassandraGameMovesRepositoryAdapter implements GameMovesRepository {

    private final CassandraGameMovesRepository cassandraGameMovesRepository;

    public CassandraGameMovesRepositoryAdapter(CassandraGameMovesRepository cassandraGameMovesRepository) {
        this.cassandraGameMovesRepository = cassandraGameMovesRepository;
    }

    @Override
    public GameMoves findByGameId(GameId gameId) {
        List<CassandraGameMoves> cassandraGameMoves = cassandraGameMovesRepository.findByGameId(gameId.getId());
        if (cassandraGameMoves != null)
            return MovesCassandraMapper.map(cassandraGameMoves);
        else
            return null;
    }

    @Override
    public void save(GameMoves gameMoves) {
        try {
            cassandraGameMovesRepository.saveAll(MovesCassandraMapper.map(gameMoves));
        } catch(Exception ex) {
            log.debug("Duplicated?", ex);
        }
    }
}
