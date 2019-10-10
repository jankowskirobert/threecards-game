package com.jvmless.threecardgame.domain.game;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Results {

    private Set<Result> results = new HashSet<>();

    public void win(HostId hostId, GamerId gamerId) {
        this.results.add(new Result(gamerId, hostId, ResultState.PLAYER_WIN_HOST_LOST, LocalDateTime.now()));
    }
    public void lost(HostId hostId, GamerId gamerId) {
            this.results.add(new Result(gamerId, hostId, ResultState.PLAYER_LOST_HOST_WIN, LocalDateTime.now()));
    }

    public void draft(HostId hostId, GamerId gamerId) {
        this.results.add(new Result(gamerId, hostId, ResultState.DRAFT, LocalDateTime.now()));
    }

    public boolean hasAny(GamerId gamerId) {
        return this.results.stream().anyMatch(x -> x.getGamerId().equals(gamerId));
    }
}
