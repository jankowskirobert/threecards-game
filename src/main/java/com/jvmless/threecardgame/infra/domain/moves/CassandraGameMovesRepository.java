package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.game.GameId;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CassandraGameMovesRepository extends CassandraRepository<CassandraGameMoves, String> {
    Optional<CassandraGameMoves> findByGameId(GameId gameId);
}
