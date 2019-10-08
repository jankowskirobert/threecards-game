package com.jvmless.threecardgame.engine;

import com.jvmless.threecardgame.domain.game.Game;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class GameEngine {

    GamesRepository gamesRepository;

    @Scheduled(fixedDelay = 42)
    public void gameRunner() {
        List<Game> games = gamesRepository.findAllActive();
        games.parallelStream().forEach(
                game -> {
                    if(game.hasTimeout()) {
                        game.timeout();
                    }
                }
        );
    }

}
