package com.jvmless.threecardgame.handlers;

import com.jvmless.threecardgame.domain.game.*;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;

import java.util.List;

public class StartGameCommandHandler {

    private final PlayerRepository playerRepository;
    private final GamesRepository gamesRepository;

    public StartGameCommandHandler(PlayerRepository playerRepository, GamesRepository gamesRepository) {
        this.playerRepository = playerRepository;
        this.gamesRepository = gamesRepository;
    }

    public void handle(StartGameCommand command) {
        GameId gameId = new GameId(command.getGameId());
        PlayerId playerId = new PlayerId(command.getHostId());
        Player player = playerRepository.find(playerId);
        if (player != null) {
            HostId hostId = new HostId(command.getHostId());
            Game activeOldGame = gamesRepository.findActiveGamesByHostId(hostId);
            if (activeOldGame == null) {
                Game newGame = new Game(gameId, hostId, command.getRoomName());
                newGame.start();
                gamesRepository.save(newGame);
            } else {
                throw new IllegalStateException("Host has active games!");
            }
        }

    }

}
