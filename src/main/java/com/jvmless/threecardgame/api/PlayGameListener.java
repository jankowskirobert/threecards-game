package com.jvmless.threecardgame.api;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.jvmless.threecardgame.handlers.commands.dto.PlayGameCommand;
import com.jvmless.threecardgame.handlers.commands.PlayGameCommandHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayGameListener implements DataListener<PlayGameCommand> {

    private final PlayGameCommandHandler playGameCommandHandler;

    public PlayGameListener(PlayGameCommandHandler playGameCommandHandler) {
        this.playGameCommandHandler = playGameCommandHandler;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, PlayGameCommand playGameCommand, AckRequest ackRequest) throws Exception {
        log.info("Play game!");
        playGameCommandHandler.handle(playGameCommand);
    }
}
