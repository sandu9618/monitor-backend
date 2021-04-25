package com.sfarc.monitor.service;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfarc.monitor.web.dto.SensorDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.net.URI;


/**
 * @author Sanduni Pavithra
 * Created on 4/18/2021
 */

@Service
public class ClientService {

    private WebSocketSession webSocketSession;

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    WebSocketHandler getClientWebSocketHandler;

    @Value("${socket.url}")
    private String clientEndpoint ;

    public void sendToClient(SensorDataDto sensorDataDto) throws IOException {
        try {
//            SingleClientSession singleClientSession = new SingleClientSession();

//            this.webSocketSession = singleClientSession.getWebSocketSession();

            var webSocketClient = new StandardWebSocketClient();
            this.webSocketSession = webSocketClient.doHandshake(getClientWebSocketHandler, new WebSocketHttpHeaders(), URI.create(clientEndpoint)).get();

            String json = ow.writeValueAsString(sensorDataDto);

            webSocketSession.sendMessage(new TextMessage(json));
            webSocketSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            webSocketSession.close();
        }
    }
}
