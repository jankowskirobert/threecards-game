package com.jvmless.threecardgame.domain.game;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
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
    private static final Integer MAX_PLAYERS = 3;
    private Set<Gamer> players = new HashSet<>();
    private Integer availableMoves = 10;
    private Moves moves = new Moves();
    private Cards cards;
    private Results results = new Results();

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

    public boolean hasMaxGamers() {
        return MAX_PLAYERS.compareTo(players.size()) == 0;
    }

    public boolean canJoin(GamerId gamerId) {
        return gameStatus.equals(GameStatus.PENDING) && !hasGamer(gamerId) && !hasMaxGamers();
    }

    public void joinMatch(GamerId gamerId) {
        if (canJoin(gamerId)) {
            players.add(new Gamer(gamerId));
        } else {
            throw new IllegalStateException(String.format("Cannot joint the game! Current players: %d (MAX: %d), already playing?: %s, status: %s",players.size(), MAX_PLAYERS, hasGamer(gamerId), gameStatus.name()));
        }
    }

    public void play(HostId hostId, Set<Card> cards) {
        if (host != null && this.isHost(hostId) && players.size() > 0) {
            gameStatus = GameStatus.HOST_SHUFFLE;
            start = LocalDateTime.now();
            this.cards = new Cards(cards);
        } else {
            throw new IllegalStateException("Not enough players to play!");
        }
    }

    public void move(int current, int destination, HostId hostId) {
        if (isOnShuffleStage() && host != null && isHost(hostId)) {
            moves.add(current, destination);
        }
    }

    public boolean isOnShuffleStage() {
        return gameStatus.equals(GameStatus.HOST_SHUFFLE);
    }

    public void acceptShuffle(HostId hostId) {
        if (hostId.equals(this.host.getHostId())) {
            this.gameStatus = GameStatus.PLAYER_GUESTING;
        }
    }

    public boolean check(GamerId gamerId, Position position) {
        //if there is result for player then he cannot check again
        if (results.hasAny(gamerId)) {
            throw new IllegalStateException("Gamer cannot check result!");
        }
        if (!hasGamer(gamerId)) {
            throw new IllegalArgumentException("Gamer has not joined this game!");
        }

        if (isOnGuestingStage()) {
            Gamer gamer = getGamer(gamerId);
            gamer.removeCheck();
            Card c = getShuffleCardOn(position);
            boolean checkResult = c.getCardType().equals(CardType.WINNING);
            if(checkResult) {
                results.win(host.getHostId(), gamerId);
            } else {
                results.lost(host.getHostId(), gamerId);

            }
            return checkResult;
        } else {
            throw new IllegalStateException(String.format("Cannot check winning card - currently game is on stage: %s!", getGameStatus()));
        }
    }

    private Card getShuffleCardOn(Position position) {
        return shuffleCard().stream()
                .filter(x -> x.getPosition().equals(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Position not find!"));
    }

    public boolean isOnGuestingStage() {
        return this.gameStatus.equals(GameStatus.PLAYER_GUESTING);
    }

    private Set<Card> shuffleCard() {
        return this.cards.shuffle(this.moves.all());
    }

    public void timeout() {
        if(isInactive()){
            this.results.draft(host.getHostId(), null);
            this.end = LocalDateTime.now();
            this.gameStatus = GameStatus.END;
        }
    }

    public int playersCount() {
        return players.size();
    }

    public void end() {
        this.end = LocalDateTime.now();
        this.gameStatus = GameStatus.END;
    }

    public boolean isHost(HostId hostId) {
        return host.match(hostId);
    }

    public boolean hasResultsForAllPlayers() {
        return this.players.stream().allMatch(x -> results.hasAny(x.getGamerId()));
    }

    private Gamer getGamer(GamerId gamerId) {
        return players.stream().filter(x -> x.getGamerId().equals(gamerId)).findFirst().orElse(null);
    }

    public void leaveMatch(GamerId gamerId) {
        Gamer gamer = getGamer(gamerId);
        if (gamer != null)
            gamer.deactivate();
    }

    public boolean isStarted() {
        return this.gameStatus.equals(GameStatus.PENDING);
    }

    public boolean isInactive() {
        return isStarted() && LocalDateTime.now().minusMinutes(2).isAfter(created);
    }

    public boolean isActive() {
        return !gameStatus.equals(GameStatus.END);
    }

    public boolean hasGamer(GamerId gamerId) {
        return players.stream().anyMatch(x -> x.getGamerId().equals(gamerId));
    }
}
