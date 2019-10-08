package com.jvmless.threecardgame.domain.game;

import java.util.List;
import java.util.Set;

public class Cards {

    private static final int MAX_CARDS = 3;
    private Set<Card> cards;

    public Cards(Set<Card> cards) {
        if (cards.size() == MAX_CARDS)
            if(cards.stream().anyMatch(x -> x.getCardType().equals(CardType.WINNING)))
                this.cards = cards;
            else
                throw new IllegalArgumentException("Missing winning card!");
        else
            throw new IllegalArgumentException("Not enough cards!");
    }

    public void shuffle(List<Move> moves) {
        if (moves.size() > 0)
            for (Move move : moves) {
                Card toSwap = cards.stream()
                        .filter(card -> card.getPosition().equals(move.getPrevious())).findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("There is no card with given position"));
                Card toBecome = cards.stream()
                        .filter(card -> card.getPosition().equals(move.getCurrent())).findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("There is no card with given position"));
                toSwap.setPosition(move.getCurrent());
                toBecome.setPosition(move.getPrevious());
            }
    }

    public boolean checkWinning(Position position) {
        Card c = cards.stream().filter(x -> x.getPosition().equals(position)).findFirst().orElseThrow(() -> new IllegalArgumentException("Position not find!"));
        return c.getCardType().equals(CardType.WINNING);
    }
}
