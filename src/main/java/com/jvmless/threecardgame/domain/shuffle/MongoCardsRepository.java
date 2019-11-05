package com.jvmless.threecardgame.domain.shuffle;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoCardsRepository extends MongoRepository<Cards, CardsId> {
}
