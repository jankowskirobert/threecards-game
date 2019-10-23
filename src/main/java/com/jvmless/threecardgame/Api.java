package com.jvmless.threecardgame;


import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class Api implements DisposableBean {
    @Autowired
    private SocketIOServer server;

    @PostConstruct
    public void setup() {
        server.start();
    }

    @Override
    public void destroy()
    {
        log.info("GOING DOWN");
        server.stop();
    }

}
