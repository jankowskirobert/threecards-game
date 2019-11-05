package com.jvmless.threecardgame.domain.shuffle;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shared.Position;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Document(collection = "moves")
public class GameMoves {
    private GameMovesId gameMovesId;
    private GameId gameId;
    private List<Move> moves = new ArrayList<>();
    private static final Integer MAX_MOVES = 10;
    private Comparator<Move> moveComparable = Comparator.comparing(Move::getMoveDate);

    public GameMoves(GameId gameId) {
        this.gameMovesId = new GameMovesId(UUID.randomUUID().toString());
        this.gameId = gameId;
        this.moves.sort(moveComparable);
    }

    public LocalDateTime last() {
        Collections.sort(moves, moveComparable);
        return moves.stream().map(x -> x.getMoveDate()).findFirst().orElse(null);
    }

    public void add(int current, int destination) {
        if (moves.size() < MAX_MOVES) {
            Move move = new Move(new Position(current), new Position(destination));
            this.moves.add(move);
        }
    }

    public List<Move> all() {
        Collections.sort(moves, moveComparable);
        return moves;
    }
}
