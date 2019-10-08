package com.jvmless.threecardgame.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Move {
    private Position previous;
    private Position current;

    public Move(Position previous, Position current) {
        if(!previous.equals(current)) {
            this.previous = previous;
            this.current = current;
        } else {
            throw new IllegalArgumentException("Positions are the same!");
        }
    }

}
