package com.jvmless.threecardgame.domain.game;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Host {
    private HostId hostId;

    public boolean match(HostId hostId) {
        return hostId.equals(hostId);
    }
}
