package com.jvmless.threecardgame.handlers.queries;

import com.jvmless.threecardgame.domain.game.Game;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.handlers.queries.dto.AvailableGame;
import com.jvmless.threecardgame.handlers.queries.dto.AvailableGameQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AvailableGameQueryHandler {

    private final GamesRepository gamesRepository;
    private final PlayerRepository playerRepository;

    public AvailableGameQueryHandler(GamesRepository gamesRepository, PlayerRepository playerRepository) {
        this.gamesRepository = gamesRepository;
        this.playerRepository = playerRepository;
    }

    @Cacheable
    public AvailableGameQuery query() {
        List<Game> games = gamesRepository.findAllActive();
        AvailableGameQuery availableGameQuery = new AvailableGameQuery();
        availableGameQuery.setAllPlayersInGame(games.size());
        availableGameQuery.setActiveGames(
                games.stream().map(game -> {
                    AvailableGame availableGame = new AvailableGame();
                    availableGame.setGameId(game.getGameId().toString());
                    availableGame.setHostId(game.getHost().getHostId().getId());
                    Player host = playerRepository.find(new PlayerId(game.getHost().getHostId().getId()));
                    availableGame.setHostName(host.getName());
                    return availableGame;
                }).collect(Collectors.toList())
        );
        return availableGameQuery;
    }

}
