package com.jvmless.threecardgame.domain.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Result {
    private GamerId gamerId;
    private ResultState resultState;

}
