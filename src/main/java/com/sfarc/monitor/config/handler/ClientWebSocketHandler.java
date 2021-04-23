package com.sfarc.monitor.config.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Sanduni Pavithra
 * Created on 4/23/2021
 */
public class ClientWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();
    private final Map<String, WebSocketSession> stringWebSocketSessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        if (session.getUri() != null){
            System.out.println("adding session .................." + session.getUri());
            String client = Objects.requireNonNull(session.getUri()).toString();
            String clientId = client.substring(client.indexOf("user") + 4);

            if (!stringWebSocketSessionMap.containsKey(clientId)){
                stringWebSocketSessionMap.put(clientId, session);
            }
            System.out.println(stringWebSocketSessionMap.keySet());
        }
        webSocketSessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("message" + message.toString());
//        for (WebSocketSession webSocketSession : webSocketSessions) {
//            if (webSocketSession.getUri() != null){
//                webSocketSession.sendMessage(message);
//            }
//        }

        stringWebSocketSessionMap
                .entrySet()
                .stream()
                .filter(v -> v.getValue().getUri() != null)
                .forEach(v -> {
                    try {
                        v.getValue().sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }
}
