package com.jvmless.threecardgame.domain.game;

import com.jvmless.threecardgame.domain.shuffle.Card;
import com.jvmless.threecardgame.domain.shared.Position;
import com.jvmless.threecardgame.domain.shuffle.CardType;
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
        game.play(hostId);
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
        game.play(hostId a);
        game.acceptShuffle(hostId);
        game.check(gamerId, new Position(2), cards);
        Assert.assertTrue(game.hasResultsForAllPlayers());
    }

    @Test(expected = IllegalStateException.class)
    public void joinPlayers() {
        GameId gameId = new GameId("G1");
        HostId hostId = new HostId("H1");
        String roomName = "Test";
        Game game = new Game(gameId, hostId, roomName);
        game.start();
        GamerId gamerId = new GamerId("JOIN1");
        game.joinMatch(gamerId);
        GamerId gamerId2 = new GamerId("JOIN2");
        game.joinMatch(gamerId2);
        GamerId gamerId3 = new GamerId("JOIN3");
        game.joinMatch(gamerId3);
        GamerId gamerId4 = new GamerId("JOIN4");
        game.joinMatch(gamerId4);
    }

}
