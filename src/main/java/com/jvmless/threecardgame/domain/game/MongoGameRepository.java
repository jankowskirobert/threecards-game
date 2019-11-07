package com.jvmless.threecardgame.domain.game;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoGameRepository extends MongoRepository<Game, GameId> {
    List<Game> findAllByHostIs(Host host);
    Game findByGameId(GameId gameId);
    List<Game> findAllByPlayersContains(Gamer gamer);
}
