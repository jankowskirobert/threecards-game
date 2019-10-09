package com.jvmless.threecardgame;

import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.game.InMemoryGameRepository;
import com.jvmless.threecardgame.domain.player.InMemoryPlayerRepository;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Repositories {
    @Bean
    public GamesRepository gamesRepository() {
        return new InMemoryGameRepository();
    }

    @Bean
    public PlayerRepository playerRepository() {
        return new InMemoryPlayerRepository();
    }


}
