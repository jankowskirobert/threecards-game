package com.jvmless.threecardgame.domain.game;

import com.jvmless.threecardgame.domain.player.PlayerId;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
public class Game {
    private GameId gameId;
    private String roomName;
    private LocalDateTime created;
    private LocalDateTime start;
    private LocalDateTime end;
    private GameStatus gameStatus;
    private Host host;
    private Set<Gamer> players;
    private Integer availableMoves;
    private List<Move> moves;
    private Cards cards;

    public Game(GameId gameId, Host host, String roomName) {
        this.gameId = gameId;
        this.roomName = roomName;
        this.host = host;
        this.created = LocalDateTime.now();
    }

    public void start() {
        if (host != null && players.size() > 0 && availableMoves > 0) {
            gameStatus = GameStatus.PENDING;
        } else {
            throw new IllegalStateException("Cannot start the game!");
        }
    }

    public void joinMatch(Gamer player) {
        if (gameStatus.equals(GameStatus.PENDING)) {
            players.add(player);
        } else {
            throw new IllegalStateException("Cannot joint the game!");
        }
    }

    public void play(PlayerId host, Set<Card> cards) {
        if (host != null && host.equals(this.host) && players.size() > 0) {
            gameStatus = GameStatus.HOST_SHUFFLE;
            start = LocalDateTime.now();
            this.cards = new Cards(cards);
        }
    }

    public void move(int current, int destination, HostId hostId) {
        if (gameStatus.equals(GameStatus.HOST_SHUFFLE) && hostId.equals(host.getHostId()) && moves.size() < availableMoves) {
            moves.add(new Move(new Position(current), new Position(destination)));
        }
    }

    public void acceptShuffle(PlayerId playerId) {
        if (playerId.equals(this.host)) {
            this.gameStatus = GameStatus.PLAYER_GUESTING;
        }
    }

    public boolean checkWinning(GamerId gamerId, Position position) {
        if (this.gameStatus.equals(GameStatus.PLAYER_GUESTING) && hasGamer(gamerId)) {
            Gamer gamer = players.stream()
                    .filter(x -> x.getGamerId().equals(gamerId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Gamer not found!"));
            gamer.removeCheck();
            Card c = cards.shuffle(moves).stream()
                    .filter(x -> x.getPosition().equals(position))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Position not find!"));
            return c.getCardType().equals(CardType.WINNING);
        } else {
            throw new IllegalStateException("Cannot check winning card!");
        }
    }

    public void timeout() {


    }

    public Gamer getGamer(GamerId gamerId) {
        return players.stream().filter(x -> x.getGamerId().equals(gamerId)).findFirst().orElse(null);
    }

    public void leaveMatch(GamerId gamerId) {
        Gamer gamer = getGamer(gamerId);
        if (gamer != null)
           gamer.deactivate();
    }

    public boolean hasTimeout() {
        return false;
    }

    public boolean isActive() {
        return !gameStatus.equals(GameStatus.END);
    }

    public boolean hasGamer(GamerId gamerId) {
        return players.stream().anyMatch(x -> x.getGamerId().equals(gamerId));
    }
}
