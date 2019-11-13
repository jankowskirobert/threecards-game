package com.jvmless.threecardgame.functional;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.game.GamesRepository;
import com.jvmless.threecardgame.domain.game.InMemoryGameRepository;
import com.jvmless.threecardgame.domain.player.InMemoryPlayerRepository;
import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.handlers.commands.StartGameCommandHandler;
import com.jvmless.threecardgame.handlers.commands.dto.StartGameCommand;
import com.jvmless.threecardgame.services.DummyGameEventService;
import com.jvmless.threecardgame.services.GameEventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class StartGame {

    private PlayerRepository playerRepository;
    private GamesRepository gamesRepository;
    private GameEventService gameEventService;

    @Before
    public void setUp() {
        gamesRepository = new InMemoryGameRepository();
        playerRepository = new InMemoryPlayerRepository();
        gameEventService = new DummyGameEventService();

        PlayerId playerId = new PlayerId("HOST");
        Player player = new Player(playerId, UUID.randomUUID().toString());
        playerRepository.save(player);

    }

    @Test
    public void startGame_positive() {
        StartGameCommandHandler startGameCommandHandler = new StartGameCommandHandler(
                playerRepository,
                gamesRepository,
                gameEventService
        );
        StartGameCommand startGameCommand = new StartGameCommand();
        startGameCommand.setGameId("GAME");
        startGameCommand.setHostId("HOST");
        startGameCommand.setRoomName("ROOM");

        startGameCommandHandler.handle(startGameCommand);

        Assert.assertTrue(gamesRepository.findByGameId(new GameId("GAME")).isStarted());
    }
}
