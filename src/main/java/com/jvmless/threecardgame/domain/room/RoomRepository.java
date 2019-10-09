package com.jvmless.threecardgame.domain.room;

public interface RoomRepository {
    Room findByOwnerId(OwnerId ownerId);

    void save(Room newRoom);
}
