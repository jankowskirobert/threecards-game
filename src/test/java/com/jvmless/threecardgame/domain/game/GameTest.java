package com.jvmless.threecardgame.domain.game;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class GameTest {

    @Test
    public void startAndPlayGame() {
        GameId gameId = new GameId("G1");
        HostId hostId = new HostId("H1");
        String roomName = "Test";
        Game game = new Game(gameId, hostId, roomName);
        game.start();

        GamerId gamerId = new GamerId("JOIN1");
        game.joinMatch(gamerId);
        HashSet<Card> cards = new HashSet<>();
        cards.add(new Card(new Position(1), CardType.LOOSING));
        cards.add(new Card(new Position(2), CardType.LOOSING));
        cards.add(new Card(new Position(3), CardType.WINNING));
        game.play(hostId, cards);
    }

    @Test
    public void startPlayAndCheckGame() {
        GameId gameId = new GameId("G1");
        HostId hostId = new HostId("H1");
        String roomName = "Test";
        Game game = new Game(gameId, hostId, roomName);
        game.start();

        GamerId gamerId = new GamerId("JOIN1");
        game.joinMatch(gamerId);
        HashSet<Card> cards = new HashSet<>();
        cards.add(new Card(new Position(1), CardType.LOOSING));
        cards.add(new Card(new Position(2), CardType.LOOSING));
        cards.add(new Card(new Position(3), CardType.WINNING));
        game.play(hostId, cards);
        game.acceptShuffle(hostId);
        game.check(gamerId, new Position(2));
        Assert.assertTrue(game.hasResultsForAllPlayers());
    }

}
