package com.jvmless.threecardgame.api;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.jvmless.threecardgame.handlers.commands.dto.StartGameCommand;
import com.jvmless.threecardgame.handlers.commands.StartGameCommandHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartGameListener implements DataListener<StartGameCommand> {

    private final StartGameCommandHandler startGameCommandHandler;

    public StartGameListener(StartGameCommandHandler startGameCommandHandler) {
        this.startGameCommandHandler = startGameCommandHandler;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, StartGameCommand startGameCommand, AckRequest ackRequest) throws Exception {
        log.info("Game start {}", startGameCommand);
        startGameCommandHandler.handle(startGameCommand);
    }
}
