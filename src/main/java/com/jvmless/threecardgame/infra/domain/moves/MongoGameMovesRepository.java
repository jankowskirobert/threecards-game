package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shuffle.GameMoves;
import com.jvmless.threecardgame.domain.shuffle.GameMovesId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoGameMovesRepository extends MongoRepository<GameMoves, GameMovesId> {
    GameMoves findByGameId(GameId gameId);
}
