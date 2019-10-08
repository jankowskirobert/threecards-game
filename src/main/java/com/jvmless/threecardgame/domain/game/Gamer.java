package com.jvmless.threecardgame.domain.game;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = "gamerId")
@Getter
public class Gamer {
    private GamerId gamerId;
    private Integer checkCount = 1;
    private GamerStatus gamerStatus;

    public Gamer(GamerId gamerId) {
        this.gamerId = gamerId;
    }

    public void removeCheck() {
        if (checkCount > 0)
            this.checkCount -= 1;
        else
            throw new IllegalStateException("Gamer has 0 checks");
    }

    public void deactivate() {
        this.gamerStatus = GamerStatus.INACTIVE;
        this.checkCount = 0;
    }

}
