package com.jvmless.threecardgame.api;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.jvmless.threecardgame.handlers.commands.dto.JoinGameCommand;
import com.jvmless.threecardgame.handlers.commands.JoinGameCommandHandler;

public class JoinGameListener implements DataListener<JoinGameCommand> {

    private final JoinGameCommandHandler joinGameCommandHandler;

    public JoinGameListener(JoinGameCommandHandler joinGameCommandHandler) {
        this.joinGameCommandHandler = joinGameCommandHandler;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, JoinGameCommand joinGameCommand, AckRequest ackRequest) throws Exception {
        joinGameCommandHandler.handle(joinGameCommand);
    }
}
