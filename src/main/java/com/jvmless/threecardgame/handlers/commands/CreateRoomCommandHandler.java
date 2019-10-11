package com.jvmless.threecardgame.handlers.commands;

import com.jvmless.threecardgame.domain.player.Player;
import com.jvmless.threecardgame.domain.player.PlayerId;
import com.jvmless.threecardgame.domain.player.PlayerRepository;
import com.jvmless.threecardgame.domain.room.*;
import com.jvmless.threecardgame.handlers.commands.dto.CreateRoomCommand;

public class CreateRoomCommandHandler {

    private final RoomRepository roomRepository;
    private final PlayerRepository playerRepository;

    public CreateRoomCommandHandler(RoomRepository roomRepository, PlayerRepository playerRepository) {
        this.roomRepository = roomRepository;
        this.playerRepository = playerRepository;
    }

    public void handle(CreateRoomCommand createRoomCommand) {
        PlayerId playerId = new PlayerId(createRoomCommand.getOwnerId());
        Player player = playerRepository.find(playerId);
        if(player != null) {
            OwnerId ownerId = new OwnerId(createRoomCommand.getOwnerId());
            Owner owner = new Owner(ownerId);
            Room room = roomRepository.findByOwnerId(ownerId);
            if(room != null) {
                RoomId roomId = new RoomId(createRoomCommand.getRoomId());
                Room newRoom = new Room(roomId, owner);
                roomRepository.save(newRoom);
            }
        }
    }

}
