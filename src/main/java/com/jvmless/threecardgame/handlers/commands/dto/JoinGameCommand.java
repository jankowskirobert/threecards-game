package com.jvmless.threecardgame.handlers.commands.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinGameCommand {
    private String playerId;
    private String gameId;
}
