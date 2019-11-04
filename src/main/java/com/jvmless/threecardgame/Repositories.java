package com.jvmless.threecardgame;

import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.game.MongoGameRepository;
import com.jvmless.threecardgame.domain.game.MongoGameRepositoryAdapter;
import com.jvmless.threecardgame.domain.shuffle.GameMovesRepository;
import com.jvmless.threecardgame.domain.shuffle.MongoGameMovesRepository;
import com.jvmless.threecardgame.domain.shuffle.MongoGameMovesRepositoryAdapter;
import com.jvmless.threecardgame.domain.player.InMemoryPlayerRepository;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Repositories {

    @Bean
    public GameMovesRepository gameMovesRepository(MongoGameMovesRepository mongoGameMovesRepository) {
        return new MongoGameMovesRepositoryAdapter(mongoGameMovesRepository);
    }

    @Bean
    public GamesRepository gamesRepository(MongoGameRepository mongoGameRepository) {
        return new MongoGameRepositoryAdapter(mongoGameRepository);
    }

    @Bean
    public PlayerRepository playerRepository() {
        return new InMemoryPlayerRepository();
    }


}
