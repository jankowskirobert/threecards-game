package com.jvmless.threecardgame.domain.game;

import com.jvmless.threecardgame.domain.player.PlayerId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Game {
    private GameId gameId;
    private String roomName;
    private LocalDateTime created;
    private LocalDateTime start;
    private GameStatus gameStatus;
    private PlayerId host;
    private Set<PlayerId> players;
    private Integer availableMoves;
    private List<Move> moves;
    private Cards cards;

    public Game(GameId gameId, PlayerId host, String roomName) {
        this.gameId = gameId;
        this.roomName = roomName;
        this.host = host;
        this.created = LocalDateTime.now();
    }

    private void start() {
        if(host != null && players.size() > 0 && availableMoves > 0) {
            gameStatus = GameStatus.PENDING;
        }
    }

    private void joinMatch(PlayerId player) {
        if(gameStatus.equals(GameStatus.PENDING)){
            players.add(player);
        } else {
            throw new IllegalStateException("Cannot joint the game!");
        }
    }



    private void play(PlayerId host, Set<Card> cards) {
        if(host != null && host.equals(this.host) && players.size() > 0) {
            gameStatus = GameStatus.HOST_SHUFFLE;
            start = LocalDateTime.now();
            this.cards = new Cards(cards);
        }
    }

    private void move(Position current, Position destination, PlayerId playerId) {
        if(gameStatus.equals(GameStatus.HOST_SHUFFLE) && playerId.equals(host) && moves.size() < availableMoves) {
            moves.add(new Move(current, destination));
        }
    }

    public boolean checkWinning(Position position) {
        if(this.gameStatus.equals(GameStatus.PLAYER_GUESTING)) {
            Card c = cards.shuffle(moves).stream().filter(x -> x.getPosition().equals(position)).findFirst().orElseThrow(() -> new IllegalArgumentException("Position not find!"));
            return c.getCardType().equals(CardType.WINNING);
        } else {
            throw new IllegalStateException("Cannot check winning card now!");
        }
    }
//
//    public Set<Card> getCards() {
//        return cards.
//    }

}
