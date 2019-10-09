package com.jvmless.threecardgame.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRoomCommand {
    private String ownerId;
    private String roomId;
}
