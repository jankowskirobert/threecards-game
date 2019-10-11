package com.jvmless.threecardgame.handlers.commands.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MakeMoveCommand {
    private String hostId;
    private int previous;
    private int current;
}
