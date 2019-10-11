package com.jvmless.threecardgame.handlers.commands.dto;

import com.jvmless.threecardgame.domain.game.HostId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartGameCommand {
    private String gameId;
    private String hostId;
    private String roomName;
}
