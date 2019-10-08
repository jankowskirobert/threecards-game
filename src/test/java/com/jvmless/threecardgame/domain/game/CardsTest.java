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
        List<Move> moves = getMoves();
        Set<Card> cardSet = getCards();
        Cards cards = new Cards(cardSet);
        cards.shuffle(moves);
        Assert.assertTrue(cards.checkWinning(new Position(1)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingWinningCard() {
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(new Position(1), CardType.LOOSING));
        cardSet.add(new Card(new Position(2), CardType.LOOSING));
        cardSet.add(new Card(new Position(3), CardType.LOOSING));
        new Cards(cardSet);
    }

    private List<Move> getMoves() {
        List<Move> moves = new ArrayList<>();
        moves.add(new Move(new Position(1), new Position(2)));
        return moves;
    }

    private Set<Card> getCards() {
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(new Position(1), CardType.LOOSING));
        cardSet.add(new Card(new Position(2), CardType.WINNING));
        cardSet.add(new Card(new Position(3), CardType.LOOSING));
        return cardSet;
    }
}