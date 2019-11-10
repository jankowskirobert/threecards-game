package com.jvmless.threecardgame.infra.domain.moves;

import com.jvmless.threecardgame.domain.game.GameId;
import com.jvmless.threecardgame.domain.shared.Position;
import com.jvmless.threecardgame.domain.shuffle.GameMoves;
import com.jvmless.threecardgame.domain.shuffle.GameMovesId;
import com.jvmless.threecardgame.domain.shuffle.Move;
import com.jvmless.threecardgame.domain.shuffle.MoveId;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MovesCassandraMapperTest {

    @Test
    public void mapTo_emptyList() {
        GameMovesId gameMovesId = new GameMovesId("Test_moves");
        GameId gameId = new GameId("Test_Game");
        GameMoves gameMoves = new GameMoves(gameMovesId, gameId);
        List<CassandraGameMoves> movesList = MovesCassandraMapper.map(gameMoves);
        assertTrue(movesList.isEmpty());
    }

    @Test
    public void mapTo_oneMove() {
        GameMovesId gameMovesId = new GameMovesId("Test_moves");
        GameId gameId = new GameId("Test_Game");
        LocalDateTime now = LocalDateTime.now();
        Move move = new Move(new MoveId("Move_1"), new Position(1), new Position(2), now);
        GameMoves gameMoves = new GameMoves(gameMovesId, gameId, Collections.singletonList(move));
        List<CassandraGameMoves> movesList = MovesCassandraMapper.map(gameMoves);
        assertFalse(movesList.isEmpty());
        CassandraGameMoves cassandraGameMoves = new CassandraGameMoves(
                "Move_1",
                "Test_moves",
                "Test_Game",
                2,
                1,
                now
        );
        assertTrue(movesList.contains(cassandraGameMoves));
    }


    @Test
    public void mapFrom() {
    }
}