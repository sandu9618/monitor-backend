package com.sfarc.monitor.service;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfarc.monitor.web.dto.SensorDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * @author Sanduni Pavithra
 * Created on 4/18/2021
 */

@Service
public class ClientService {

    private WebSocketSession webSocketSession;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    WebSocketHandler getChatWebSocketHandler;

    public void sendToClient(SensorDataDto sensorDataDto) throws IOException {
        try {
            SingleClientSession singleClientSession = new SingleClientSession();

            this.webSocketSession = singleClientSession.getWebSocketSession();

            String json = ow.writeValueAsString(sensorDataDto);

            webSocketSession.sendMessage(new TextMessage(json));
            webSocketSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            webSocketSession.close();
        }
    }
}
