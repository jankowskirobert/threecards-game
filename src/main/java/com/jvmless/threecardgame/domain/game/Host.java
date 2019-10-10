package com.jvmless.threecardgame.domain.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Host {
    private HostId hostId;

    public boolean match(HostId hostId) {
        return hostId.equals(hostId);
    }
}
