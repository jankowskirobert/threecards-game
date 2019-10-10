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

    public Game(GameId gameId, HostId host, String roomName) {
        this.gameId = gameId;
        this.roomName = roomName;
        this.host = new Host(host);
        this.created = LocalDateTime.now();
        this.gameStatus = GameStatus.CREATED;
    }

    public void start() {
        if (host != null && availableMoves > 0 && isActive()) {
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
        if (host != null && host.equals(this.host.getHostId()) && players.size() > 0) {
            gameStatus = GameStatus.HOST_SHUFFLE;
            start = LocalDateTime.now();
            this.cards = new Cards(cards);
        } else {
            throw new IllegalStateException("Not enough players to play!");
        }
    }

    public void move(int current, int destination, HostId hostId) {
        if (gameStatus.equals(GameStatus.HOST_SHUFFLE) && hostId.equals(host.getHostId()) && moves.size() < availableMoves) {
            moves.add(new Move(new Position(current), new Position(destination)));
        }
    }

    public void acceptShuffle(HostId hostId) {
        if (hostId.equals(this.host.getHostId())) {
            this.gameStatus = GameStatus.PLAYER_GUESTING;
        }
    }

    public boolean checkWinning(GamerId gamerId, Position position) {
        if(!hasGamer(gamerId)) {
            throw new IllegalArgumentException("Gamer has not joined this game!");
        }

        if (isOnGuestingStage()) {
            Gamer gamer = players.stream()
                    .filter(x -> x.getGamerId().equals(gamerId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Gamer not found!"));
            gamer.removeCheck();
            Card c = shuffleCard().stream()
                    .filter(x -> x.getPosition().equals(position))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Position not find!"));
            return c.getCardType().equals(CardType.WINNING);
        } else {
            throw new IllegalStateException(String.format("Cannot check winning card - currently game is on stage: %s!", getGameStatus()));
        }
    }

    public boolean isOnGuestingStage() {
        return this.gameStatus.equals(GameStatus.PLAYER_GUESTING);
    }

    private Set<Card> shuffleCard() {
        return this.cards.shuffle(this.moves);
    }

    public void timeout() {


    }

    private Gamer getGamer(GamerId gamerId) {
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
