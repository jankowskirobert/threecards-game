package com.jvmless.threecardgame.api;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.jvmless.threecardgame.handlers.CreateRoomCommand;
import com.jvmless.threecardgame.handlers.CreateRoomCommandHandler;

public class CreateRoomListener implements DataListener<CreateRoomCommand> {

    private final CreateRoomCommandHandler createRoomCommandHandler;

    public CreateRoomListener(CreateRoomCommandHandler createRoomCommandHandler) {
        this.createRoomCommandHandler = createRoomCommandHandler;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, CreateRoomCommand createRoomCommand, AckRequest ackRequest) throws Exception {
        createRoomCommandHandler.handle(createRoomCommand);
    }
}
