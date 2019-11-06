package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shuffle.GameMovesId;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CassandraGameMovesRepository extends CassandraRepository<CassandraGameMoves, String> {
    Optional<CassandraGameMoves> findByMovesIdAndGameId(GameMovesId gameMovesId, GameId gameId);
}
