package com.jvmless.threecardgame.domain.game;

import lombok.*;

@Getter
@EqualsAndHashCode(of = "place")
public class Position {
    private static final int MAX_POSITION = 3;
    private Integer place;

    public Position(Integer place) {
        if(place <= MAX_POSITION && place > 0) {
            this.place = place;
        } else {
            throw new IllegalArgumentException("Illegal position number!");
        }
    }
}
