package com.jvmless.threecardgame.domain.room;

import java.util.Set;

public class Room {
    private RoomId roomId;
    private Set<Guest> guests;
    private Owner owner;
    private RoomState roomState;

    public Room(RoomId roomId, Owner owner) {
        this.roomId = roomId;
        this.owner = owner;
    }

    public void kickGuest(GuestId guestId, String reason, long minutes) {
        if(isActive() && hasGuest(guestId)) {
            getGuest(guestId).kick(new Penalty());
        }
    }

    public void join(GuestId guestId) {
        Guest guest = new Guest(guestId);
        guests.add(guest);
    }

    private Guest getGuest(GuestId guestId) {
        return guests.stream().filter(x -> x.getGuestId().equals(guestId)).findFirst().orElse(null);
    }

    public boolean hasGuest(GuestId guestId) {
        return guests.stream().anyMatch(guest -> guest.getGuestId().equals(guestId));
    }

    public boolean isActive() {
        return roomState.equals(RoomState.ACTIVE);
    }
}
