package com.jvmless.threecardgame.handlers.commands;

import com.jvmless.threecardgame.domain.game.*;
import com.jvmless.threecardgame.domain.shuffle.GameMoves;
import com.jvmless.threecardgame.domain.shuffle.GameMovesRepository;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.handlers.commands.dto.MakeMoveCommand;

public class MakeMoveCommandHandler {

    private final GamesRepository gamesRepository;
    private final PlayerRepository playerRepository;
    private final GameMovesRepository gameMovesRepository;

    public MakeMoveCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository, GameMovesRepository gameMovesRepository) {
        this.gamesRepository = gamesRepository;
        this.playerRepository = playerRepository;
        this.gameMovesRepository = gameMovesRepository;
    }

    public void handle(MakeMoveCommand makeMoveCommand) {
        HostId hostId = new HostId(makeMoveCommand.getHostId());
        PlayerId playerId = new PlayerId(makeMoveCommand.getHostId());
        Player player = playerRepository.find(playerId);
        if (player != null) {
            Game game = gamesRepository.findActiveGamesByHostId(hostId);
            if (game != null && game.isOnShuffleStage()) {
                GameMoves gameMoves = gameMovesRepository.findByGameId(game.getGameId());
                gameMoves.add(makeMoveCommand.getPrevious(), makeMoveCommand.getCurrent());
                gameMovesRepository.save(gameMoves);
            }
        }
    }
}
