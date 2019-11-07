package com.jvmless.threecardgame.domain.shuffle;

import com.google.common.collect.ImmutableCollection;
import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shared.Position;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "moves")
public class GameMoves {
    @Id
    private GameMovesId gameMovesId;
    @Indexed(unique = true)
    private GameId gameId;
    private List<Move> moves = new ArrayList<>();
    private static final Integer MAX_MOVES = 10;
    @Transient
    private Comparator<Move> moveComparable = Comparator.comparing(Move::getMoveDate);

    public GameMoves(GameMovesId gameMovesId, GameId gameId) {
        this.gameMovesId = gameMovesId;
        this.gameId = gameId;
        this.moves.sort(moveComparable);
    }

    public GameMoves(GameMovesId gameMovesId, GameId gameId, List<Move> moves) {
        this.gameMovesId = gameMovesId;
        this.gameId = gameId;
        Collections.unmodifiableList(moves).stream().forEach(move -> {
            add(move.getMoveId(), move.getCurrent().getPlace(), move.getPrevious().getPlace(), move.getMoveDate());
        });
        this.moves.sort(moveComparable);
    }


    public LocalDateTime last() {
        Collections.sort(moves, moveComparable);
        return moves.stream().map(x -> x.getMoveDate()).findFirst().orElse(null);
    }

    public void add(int current, int destination, LocalDateTime localDateTime) {
        if (moves.size() < MAX_MOVES) {
            String id = UUID.randomUUID().toString();
            Move move = new Move(new MoveId(id), new Position(current), new Position(destination), localDateTime);
            this.moves.add(move);
        } else {
            throw new IllegalArgumentException("Cannot add more moves, reach MAX of: " + MAX_MOVES);
        }
    }
    private void add(MoveId moveId, int current, int destination, LocalDateTime localDateTime) {
        if (moves.size() < MAX_MOVES) {
            Move move = new Move(moveId, new Position(current), new Position(destination), localDateTime);
            this.moves.add(move);
        } else {
            throw new IllegalArgumentException("Cannot add more moves, reach MAX of: " + MAX_MOVES);
        }
    }

    public GameMovesId getGameMovesId() {
        return gameMovesId;
    }

    public GameId getGameId() {
        return gameId;
    }

    public List<Move> getMoves() {
        return Collections.unmodifiableList(moves);
    }

    public List<Move> all() {
        Collections.sort(moves, moveComparable);
        return moves;
    }
}
