package com.jvmless.threecardgame;


import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Api {
    @Autowired
    private SocketIOServer server;

    @PostConstruct
    public void setup() {
        server.start();
    }

    @PreDestroy
    public void tear() {
        server.stop();
    }

}
