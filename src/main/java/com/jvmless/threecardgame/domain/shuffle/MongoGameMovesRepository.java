package com.jvmless.threecardgame.domain.shuffle;

import com.jvmless.threecardgame.domain.game.GameId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoGameMovesRepository extends MongoRepository<GameMoves, GameMovesId> {
    GameMoves findByGameId(GameId gameId);
}
