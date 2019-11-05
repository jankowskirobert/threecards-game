package com.jvmless.threecardgame.domain.shuffle;

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
