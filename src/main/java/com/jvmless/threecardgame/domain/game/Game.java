package com.jvmless.threecardgame.domain.game;

import com.jvmless.threecardgame.domain.player.PlayerId;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
    private Set<Gamer> players = new HashSet<>();
    private Integer availableMoves = 10;
    private List<Move> moves = new ArrayList<>();
    private Cards cards;

    public Game(GameId gameId, Host host, String roomName) {
        this.gameId = gameId;
        this.roomName = roomName;
        this.host = host;
        this.created = LocalDateTime.now();
        this.gameStatus = GameStatus.CREATED;
    }

    public void start() {
        if (host != null && players.size() > 0 && availableMoves > 0 && isActive()) {
            gameStatus = GameStatus.PENDING;
        } else {
            throw new IllegalStateException(String.format("Cannot start the game! Game has %d players, %d available moves!", players.size(), availableMoves));
        }
    }

    public void joinMatch(GamerId gamerId) {
        if (gameStatus.equals(GameStatus.PENDING)) {
            players.add(new Gamer(gamerId));
        } else {
            throw new IllegalStateException("Cannot joint the game!");
        }
    }

    public void play(HostId host, Set<Card> cards) {
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
