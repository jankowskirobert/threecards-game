package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.shuffle.Cards;
import com.jvmless.threecardgame.domain.shuffle.CardsRepository;
import com.jvmless.threecardgame.domain.shuffle.MongoCardsRepository;

public class MongoCardsRespositoryAdapter implements CardsRepository {

    private final MongoCardsRepository mongoCardsRepository;


    public MongoCardsRespositoryAdapter(MongoCardsRepository mongoCardsRepository) {
        this.mongoCardsRepository = mongoCardsRepository;
    }

    @Override
    public void save(Cards cards) {
        mongoCardsRepository.save(cards);
    }
}
