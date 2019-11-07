package com.jvmless.threecardgame.engine;

import com.corundumstudio.socketio.SocketIOServer;
import com.jvmless.threecardgame.domain.game.Game;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
public class GameEngine {

    private final GamesRepository gamesRepository;
    private final SocketIOServer socketIOServer;

    public GameEngine(GamesRepository gamesRepository, SocketIOServer socketIOServer) {
        this.gamesRepository = gamesRepository;
        this.socketIOServer = socketIOServer;
    }

    @Scheduled(fixedDelay = 42)
    public void gameRunner() {
        List<Game> games = gamesRepository.findAllActive();
        games.parallelStream().forEach(
                game -> {
                    if(game.hasResultsForAllPlayers() && game.isOnGuestingStage() ) {
                        game.end();
                        log.info("Game: {} ended and has been shut down!", game.getGameId());
                    }
                    if(game.isStartedAndInactive()) {
                        game.timeout();
                        log.info("Game: {} timeout has been shut down!", game.getGameId());

                    }
                    if(game.isOnShuffleStage() && game.hasMaxMoves() && game.isInactiveMinutes(2)){
                        game.end();
                        log.info("Game: {} timeout has been shut down!", game.getGameId());
                    }
                    gamesRepository.save(game);
                }
        );
    }

}
