package com.jvmless.threecardgame.handlers.queries.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvailableGame {
    private String roomName;
    private String gameId;
    private int playersOnline;
    private String hostName;
    private String hostId;
    private List<Player> playerList;
}
