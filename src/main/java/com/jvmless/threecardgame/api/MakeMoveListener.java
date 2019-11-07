package com.jvmless.threecardgame.api;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.jvmless.threecardgame.handlers.commands.dto.MakeMoveCommand;
import com.jvmless.threecardgame.handlers.commands.MakeMoveCommandHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MakeMoveListener implements DataListener<MakeMoveCommand> {

    private final MakeMoveCommandHandler makeMoveCommandHandler;

    public MakeMoveListener(MakeMoveCommandHandler makeMoveCommandHandler) {
        this.makeMoveCommandHandler = makeMoveCommandHandler;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, MakeMoveCommand makeMoveCommand, AckRequest ackRequest) throws Exception {
        log.debug("Make move {}", makeMoveCommand);
        makeMoveCommandHandler.handle(makeMoveCommand);
    }
}
