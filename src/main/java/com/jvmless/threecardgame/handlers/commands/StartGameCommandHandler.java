package com.jvmless.threecardgame.handlers.commands;

import com.jvmless.threecardgame.handlers.commands.dto.StartGameCommand;
import com.jvmless.threecardgame.services.GameEventService;
import com.jvmless.threecardgame.domain.game.*;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;

public class StartGameCommandHandler {

    private final PlayerRepository playerRepository;
    private final GamesRepository gamesRepository;
    private final GameEventService gameEventService;

    public StartGameCommandHandler(PlayerRepository playerRepository, GamesRepository gamesRepository, GameEventService gameEventService) {
        this.playerRepository = playerRepository;
        this.gamesRepository = gamesRepository;
        this.gameEventService = gameEventService;
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
                gameEventService.sendGameStartedEvent(gameId);
                gameEventService.sendActiveGames();
            } else {
                throw new IllegalStateException("Host has active games!");
            }
        }

    }

}
