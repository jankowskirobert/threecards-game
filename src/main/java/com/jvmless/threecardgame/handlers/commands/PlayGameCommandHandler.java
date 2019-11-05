package com.jvmless.threecardgame.handlers.commands;

import com.jvmless.threecardgame.domain.game.Game;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.game.HostId;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.domain.shuffle.Cards;
import com.jvmless.threecardgame.domain.shuffle.CardsRepository;
import com.jvmless.threecardgame.handlers.commands.dto.PlayGameCommand;

import java.util.HashSet;

public class PlayGameCommandHandler {

    private final GamesRepository gamesRepository;
    private final PlayerRepository playerRepository;
    private final CardsRepository cardsRepository;

    public PlayGameCommandHandler(GamesRepository gamesRepository, PlayerRepository playerRepository, CardsRepository cardsRepository) {
        this.gamesRepository = gamesRepository;
        this.playerRepository = playerRepository;
        this.cardsRepository = cardsRepository;
    }

    public void handle(PlayGameCommand playGameCommand) {
        HostId hostId = new HostId(playGameCommand.getHostId());
        PlayerId playerId = new PlayerId(playGameCommand.getHostId());
        Player player = playerRepository.find(playerId);
        if (player != null) {
            Game game = gamesRepository.findActiveGamesByHostId(hostId);
            if (game != null) {
                Cards cards = new Cards(game.getGameId(), new HashSet<>(playGameCommand.getCards()));
                cardsRepository.save(cards);
                game.play(hostId);
            }
        }
    }

}
