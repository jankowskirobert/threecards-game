package com.jvmless.threecardgame.handlers.commands;

import com.jvmless.threecardgame.domain.game.*;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.handlers.commands.dto.JoinGameCommand;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
                    log.debug("Player: {} joined game: {}", playerId, gameId);
                    game.joinMatch(gamerId);
                    gamesRepository.save(game);
                }
            }
        }
    }

}
