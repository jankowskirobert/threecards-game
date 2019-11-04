package com.jvmless.threecardgame.domain.shuffle;

import com.jvmless.threecardgame.domain.shared.Position;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
class Move {
    private Position previous;
    private Position current;
    private LocalDateTime moveDate;

    protected Move(Position previous, Position current) {
        if(!previous.equals(current)) {
            this.previous = previous;
            this.current = current;
            this.moveDate = LocalDateTime.now();
        } else {
            throw new IllegalArgumentException("Positions are the same!");
        }
    }

}
