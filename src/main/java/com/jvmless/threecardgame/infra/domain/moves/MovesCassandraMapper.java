package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shared.Position;
import com.jvmless.threecardgame.domain.shuffle.GameMoves;
import com.jvmless.threecardgame.domain.shuffle.GameMovesId;
import com.jvmless.threecardgame.domain.shuffle.Move;
import com.jvmless.threecardgame.domain.shuffle.MoveId;

import java.util.List;
import java.util.stream.Collectors;

public class MovesCassandraMapper {

    public static List<CassandraGameMoves> map(GameMoves gameMoves) {
        return gameMoves.getMoves().stream().map(
                move -> {
                    CassandraGameMoves cassandraGameMoves = new CassandraGameMoves();
                    cassandraGameMoves.setMoveId(move.getMoveId().getId());
                    cassandraGameMoves.setMovesId(gameMoves.getGameMovesId().getId());
                    cassandraGameMoves.setGameId(gameMoves.getGameId().getId());
                    cassandraGameMoves.setCurrent(move.getCurrent().getPlace());
                    cassandraGameMoves.setPrevious(move.getPrevious().getPlace());
                    cassandraGameMoves.setMoveTime(move.getMoveDate());
                    return cassandraGameMoves;
                }
        ).collect(Collectors.toList());
    }

    public static GameMoves map(List<CassandraGameMoves> cassandraGameMoves) {
        if(!cassandraGameMoves.isEmpty()) {
            List<Move> moves = cassandraGameMoves
                    .stream()
                    .map(move -> new Move(new MoveId(move.getMoveId()), new Position(move.getPrevious()), new Position(move.getCurrent()), move.getMoveTime()))
                    .collect(Collectors.toList());
            GameMoves gameMoves = new GameMoves(new GameMovesId(cassandraGameMoves.get(0).getMovesId()), new GameId(cassandraGameMoves.get(0).getGameId()), moves);
            return gameMoves;
        } else {
            return null;
        }
    }
}
