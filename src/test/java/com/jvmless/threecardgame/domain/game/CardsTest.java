package com.jvmless.threecardgame.domain.game;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CardsTest {

    @Test
    public void shuffle() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(new Position(1), new Position(2)));
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(new Position(1), CardType.LOOSING));
        cardSet.add(new Card(new Position(2), CardType.WINNING));
        cardSet.add(new Card(new Position(3), CardType.LOOSING));
        Cards cards = new Cards(cardSet);
        cards.shuffle(moves);
        Assert.assertTrue(cards.checkWinning(new Position(1)));
    }
}