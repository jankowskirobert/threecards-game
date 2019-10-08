package com.jvmless.threecardgame.domain.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private PlayerId playerId;
    private String name;

}
