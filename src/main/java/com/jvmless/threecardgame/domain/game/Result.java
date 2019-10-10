package com.jvmless.threecardgame.domain.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Result {
    private GamerId gamerId;
    private HostId hostId;
    private ResultState resultState;
    private LocalDateTime resultTime;
}
