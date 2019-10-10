package com.jvmless.threecardgame.engine;

import com.jvmless.threecardgame.domain.game.Game;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
public class GameEngine {

    private final GamesRepository gamesRepository;

    public GameEngine(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @Scheduled(fixedDelay = 42)
    public void gameRunner() {
        List<Game> games = gamesRepository.findAllActive();
        games.parallelStream().forEach(
                game -> {
                    if(game.hasResultsForAllPlayers() && game.isOnGuestingStage()) {
                        game.end();
                    }
                }
        );
    }

}
