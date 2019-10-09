package com.jvmless.threecardgame.api;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.jvmless.threecardgame.handlers.MakeMoveCommand;
import com.jvmless.threecardgame.handlers.MakeMoveCommandHandler;

public class MakeMoveListener implements DataListener<MakeMoveCommand> {

    private final MakeMoveCommandHandler makeMoveCommandHandler;

    public MakeMoveListener(MakeMoveCommandHandler makeMoveCommandHandler) {
        this.makeMoveCommandHandler = makeMoveCommandHandler;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, MakeMoveCommand makeMoveCommand, AckRequest ackRequest) throws Exception {
        makeMoveCommandHandler.handle(makeMoveCommand);
    }
}
