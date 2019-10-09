package com.jvmless.threecardgame.domain.room;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Guest {
    private GuestId guestId;
    private GuestState guestState;
    private Penalty penalty;
    private LocalDateTime joinTime;

    public Guest(GuestId guestId) {
        this.guestId = guestId;
        this.guestState = GuestState.OK;
        this.penalty = null;
        this.joinTime = LocalDateTime.now();
    }

    public void kick(Penalty penalty) {
        this.guestState = GuestState.KICKED;
        this.penalty = penalty;
    }
}
