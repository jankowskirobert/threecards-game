package com.jvmless.threecardgame.domain.shuffle;

import com.jvmless.threecardgame.domain.shared.Position;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Move {
    private MoveId moveId;
    private Position previous;
    private Position current;
    private LocalDateTime moveDate;

    public Move(MoveId moveId, Position previous, Position current, LocalDateTime localDateTime) {
        if(!previous.equals(current)) {
            this.moveId = moveId;
            this.previous = previous;
            this.current = current;
            this.moveDate = localDateTime;
        } else {
            throw new IllegalArgumentException("Positions are the same!");
        }
    }

}
