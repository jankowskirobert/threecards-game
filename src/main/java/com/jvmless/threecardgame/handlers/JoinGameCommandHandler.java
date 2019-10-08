package com.jvmless.threecardgame.handlers;

import com.jvmless.threecardgame.domain.game.*;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;

import java.util.List;

public class JoinGameCommandHandler {

    private final GamesRepository gamesRepository;
    private final PlayerRepository playerRepository;

    public JoinGameCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        this.gamesRepository = gamesRepository;
        this.playerRepository = playerRepository;
    }

    public void handle(JoinGameCommand joinGameCommand) {
        PlayerId playerId = new PlayerId(joinGameCommand.getPlayerId());
        Player player = playerRepository.find(playerId);
        if (player != null) {
            GamerId gamerId = new GamerId(joinGameCommand.getGameId());
            Game activeOldGame = gamesRepository.findActiveGamesByGamerId(gamerId);
            if (activeOldGame == null) {
                GameId gameId = new GameId(joinGameCommand.getGameId());
                Game game = gamesRepository.findByGameId(gameId);
                if (game != null) {
                    Gamer gamer = new Gamer(gamerId);
                    game.joinMatch(gamer);
                    gamesRepository.save(game);
                }
            }
        }
    }

}
