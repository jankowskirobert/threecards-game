package com.jvmless.threecardgame.handlers;

import com.jvmless.threecardgame.domain.game.Game;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.game.HostId;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;

public class MakeMoveCommandHandler {

    private final GamesRepository gamesRepository;
    private final PlayerRepository playerRepository;

    public MakeMoveCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        this.gamesRepository = gamesRepository;
        this.playerRepository = playerRepository;
    }

    public void handle(MakeMoveCommand makeMoveCommand) {
        HostId hostId = new HostId(makeMoveCommand.getHostId());
        PlayerId playerId = new PlayerId(makeMoveCommand.getHostId());
        Player player = playerRepository.find(playerId);
        if (player != null) {
            Game game = gamesRepository.findActiveGamesByHostId(hostId);
            if (game != null) {
                game.move(makeMoveCommand.getPrevious(), makeMoveCommand.getCurrent(), hostId);
            }
        }
    }
}
