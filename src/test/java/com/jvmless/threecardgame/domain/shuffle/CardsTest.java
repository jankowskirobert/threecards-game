package com.jvmless.threecardgame.domain.shuffle;

import com.jvmless.threecardgame.domain.shared.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardsTest {

    @Test
    public void shuffle() {
        //given
        List<Move> moves = getMoves();
        Set<Card> cardSet = getCards();
        Cards cards = new Cards(cardSet);
        //when
        Set<Card> out = cards.shuffle(moves);
        //then
        Assert.assertTrue(out.stream().filter(x -> x.getPosition().equals(new Position(2))).findFirst().get().getCardType().equals(CardType.WINNING));
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
        cardSet.add(new Card(new Position(1), CardType.WINNING));
        cardSet.add(new Card(new Position(2), CardType.LOOSING));
        cardSet.add(new Card(new Position(3), CardType.LOOSING));
        return cardSet;
    }
}
