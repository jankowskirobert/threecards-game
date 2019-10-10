package com.jvmless.threecardgame.domain.game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Moves {
    private List<Move> moves = new ArrayList<>();
    private static final Integer MAX_MOVES = 10;
    private Comparator<Move> moveComparable = Comparator.comparing(Move::getMoveDate);
    public Moves() {
        moves.sort(moveComparable);
    }

    public LocalDateTime last() {
        Collections.sort(moves, moveComparable);
        return moves.stream().map(x -> x.getMoveDate()).findFirst().orElse(null);
    }

    public void add(int current, int destination) {
        if(moves.size() < MAX_MOVES) {
            Move move = new Move(new Position(current), new Position(destination));
            this.moves.add(move);
        }
    }

    public List<Move> all() {
        Collections.sort(moves, moveComparable);
        return moves;
    }
}
