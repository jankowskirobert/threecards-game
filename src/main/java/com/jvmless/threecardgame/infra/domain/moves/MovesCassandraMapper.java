package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shared.Position;
import com.jvmless.threecardgame.domain.shuffle.GameMoves;
import com.jvmless.threecardgame.domain.shuffle.GameMovesId;
import com.jvmless.threecardgame.domain.shuffle.Move;

import java.util.List;
import java.util.stream.Collectors;

public class MovesCassandraMapper {

    public static CassandraGameMoves map(GameMoves gameMoves) {
        CassandraGameMoves cassandraGameMoves = new CassandraGameMoves();
        cassandraGameMoves.setGameId(gameMoves.getGameId().getId());
        cassandraGameMoves.setMovesId(gameMoves.getGameMovesId().getId());
        cassandraGameMoves.setMoves(
                gameMoves.getMoves().stream()
                        .map(move -> {
                            CassandraGameMove cassandraGameMove = new CassandraGameMove();
                            cassandraGameMove.setCurrent(move.getCurrent().getPlace());
                            cassandraGameMove.setMoveTime(move.getMoveDate());
                            cassandraGameMove.setPrevious(move.getPrevious().getPlace());
                            return cassandraGameMove;
                        }).collect(Collectors.toList())
        );
        return cassandraGameMoves;
    }

    public static GameMoves map(CassandraGameMoves cassandraGameMoves) {
        List<Move> moves = cassandraGameMoves.getMoves()
                .stream()
                .map(move -> new Move(new Position(move.getPrevious()), new Position(move.getCurrent()), move.getMoveTime()))
                .collect(Collectors.toList());
        GameMoves gameMoves = new GameMoves(new GameMovesId(cassandraGameMoves.getMovesId()), new GameId(cassandraGameMoves.getGameId()), moves);
        return gameMoves;
    }
}
