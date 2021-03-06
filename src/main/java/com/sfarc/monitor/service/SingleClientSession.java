package com.sfarc.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;
import java.util.concurrent.ExecutionException;


/**
 * @author Sanduni Pavithra
 * Created on 4/23/2021
 */

@Service
public class SingleClientSession {

    @Autowired
    private WebSocketHandler getClientWebSocketHandler;

    @Value("${socket.url}")
    private String clientEndpoint ;

    private volatile WebSocketSession clientSession;

    public WebSocketSession getWebSocketSession(){
        if (clientSession == null){
            synchronized (this){
                if (clientSession == null){
                    try{
                        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
                        clientSession = webSocketClient.doHandshake(getClientWebSocketHandler, new WebSocketHttpHeaders(),
                                URI.create(clientEndpoint)).get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        // TODO Handle exception
                    }
                }
            }
        }
        return clientSession;
    }


}
