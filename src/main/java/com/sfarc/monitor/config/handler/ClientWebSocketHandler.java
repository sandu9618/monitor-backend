package com.sfarc.monitor.config.handler;

import com.sfarc.monitor.service.SensorService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Sanduni Pavithra
 * Created on 4/23/2021
 */

@Component
public class ClientWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> stringWebSocketSessionMap = new HashMap<>();

    @Autowired
    SensorService sensorService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        if (session.getUri() != null){
            System.out.println("adding session .................." + session.getUri());
            String client = Objects.requireNonNull(session.getUri()).toString();
            String clientId = client.substring(client.indexOf("user") + 5);

            if (!stringWebSocketSessionMap.containsKey(clientId)){
                stringWebSocketSessionMap.put(clientId, session);
            }
            System.out.println(stringWebSocketSessionMap.keySet());
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage sensorData) throws Exception {
        System.out.println("message" + sensorData.getPayload());

        JSONObject sensorJson  = new JSONObject(sensorData.getPayload());
        System.out.println(sensorJson.get("sensorId"));

        List<String> subscriberIds = sensorService.getCurrentSubscriberIds(sensorJson.get("sensorId").toString());

        System.out.println("subscribers = " + subscriberIds);

        Map<String, WebSocketSession> sensorSubscribersSessions = subscriberIds
                .stream()
                .filter(stringWebSocketSessionMap::containsKey)
                .collect(Collectors.toMap(Function.identity(), stringWebSocketSessionMap::get)) ;

        sensorSubscribersSessions.entrySet()
                .stream()
                .parallel()
                .filter(v -> v.getValue().getUri() != null)
                .forEach(v -> {
                    try {
                        v.getValue().sendMessage(sensorData);
                    } catch (IOException e) {
                        e.printStackTrace();
                        // TODO Handle Exception
                    }
                });
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        List<String> toRemove = new ArrayList<>();
        stringWebSocketSessionMap.entrySet()
                .stream()
                .filter(v -> session.equals(v.getValue()))
                .peek(v -> {
                    try {
                        v.getValue().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .forEach(k -> toRemove.add(k.getKey()));

        toRemove.forEach(stringWebSocketSessionMap::remove);

    }
}
