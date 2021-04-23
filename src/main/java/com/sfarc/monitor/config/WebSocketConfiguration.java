package com.sfarc.monitor.config;

import com.sfarc.monitor.config.handler.ClientWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

import java.util.concurrent.ExecutionException;

import static com.sfarc.monitor.config.Constants.CLIENT_ENDPOINT;

/**
 * @author Sanduni Pavithra
 * Created on 4/12/2021
 */

@Configuration
@EnableWebSocket
public class WebSocketConfiguration  implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        try {
            webSocketHandlerRegistry
                    .addHandler(getClientWebSocketHandler(), CLIENT_ENDPOINT)
                    .setAllowedOrigins("*");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public WebSocketHandler getClientWebSocketHandler() throws ExecutionException, InterruptedException{
        return new ClientWebSocketHandler();
    }
}
