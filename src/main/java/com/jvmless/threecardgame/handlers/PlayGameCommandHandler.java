package com.jvmless.threecardgame.handlers;

import com.jvmless.threecardgame.domain.game.Game;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.game.HostId;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.domain.room.GuestId;

import java.util.HashSet;

public class PlayGameCommandHandler {

    private final GamesRepository gamesRepository;
    private final PlayerRepository playerRepository;

    public PlayGameCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        this.gamesRepository = gamesRepository;
        this.playerRepository = playerRepository;
    }

    public void handle(PlayGameCommand playGameCommand) {
        HostId hostId = new HostId(playGameCommand.getHostId());
        PlayerId playerId = new PlayerId(playGameCommand.getHostId());
        Player player = playerRepository.find(playerId);
        if(player != null) {
            Game game = gamesRepository.findActiveGamesByHostId(hostId);
            if (game != null) {
                game.play(hostId, new HashSet<>(playGameCommand.getCards()));
            }
        }
    }

}
