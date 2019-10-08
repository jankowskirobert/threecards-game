package com.jvmless.threecardgame.domain.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PlayerId {
    private String sessionId;
}
