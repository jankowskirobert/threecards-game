package com.jvmless.threecardgame.domain.game;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Results {

    private Set<Result> results = new HashSet<>();

    public void add(GamerId gamerId, boolean result) {
        if(result)
        this.results.add(new Result(gamerId, ResultState.WIN, LocalDateTime.now()));
        else
            this.results.add(new Result(gamerId, ResultState.LOST, LocalDateTime.now()));

    }

    public boolean hasAny(GamerId gamerId) {
        return this.results.stream().anyMatch(x -> x.getGamerId().equals(gamerId));
    }
}
