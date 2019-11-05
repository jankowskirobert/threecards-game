package com.jvmless.threecardgame.domain.shuffle;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shared.Position;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@Document(collection = "cards")
public class Cards {
    @Id
    private CardsId cardsId;
    @Indexed(unique = true)
    private GameId gameId;
    private static final int MAX_CARDS = 3;
    private static final int WINNING = 1;
    private static final int LOOSING = 2;
    private Set<Card> cards;

    public Cards(GameId gameId, Set<Card> cards) {
        this.cardsId = new CardsId(UUID.randomUUID().toString());
        this.gameId = gameId;
        if (cards.size() == MAX_CARDS)
            if(getCountOfType(cards, CardType.WINNING) == WINNING && getCountOfType(cards, CardType.LOOSING) == LOOSING)
                this.cards = Collections.unmodifiableSet(cards);
            else
                throw new IllegalArgumentException("Missing winning or loosing card!");
        else
            throw new IllegalArgumentException("Not enough cards!");
    }

    private long getCountOfType(Set<Card> cards, CardType winning) {
        return cards.stream().filter(x -> x.getCardType().equals(winning)).count();
    }

    protected Set<Card> shuffle(List<Move> moves) {
        Set<Card> copied = deepCopyCards();
        if (moves.size() > 0)
            for (Move move : moves) {
                Card toSwap = copied.stream()
                        .filter(card -> card.getPosition().equals(move.getPrevious())).findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("There is no card with given position"));
                Card toBecome = copied.stream()
                        .filter(card -> card.getPosition().equals(move.getCurrent())).findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("There is no card with given position"));
                toSwap.setPosition(move.getCurrent());
                toBecome.setPosition(move.getPrevious());
            }
        return copied;
    }

    private Set<Card> deepCopyCards() {
        Set<Card> copied = new HashSet<>();
        for(Card c : this.cards) {
            copied.add(new Card(new Position(c.getPosition().getPlace()), CardType.valueOf(c.getCardType().name())));
        }
        return copied;
    }


}
