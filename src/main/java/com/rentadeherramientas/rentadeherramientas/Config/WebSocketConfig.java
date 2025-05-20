package com.rentadeherramientas.rentadeherramientas.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;

@Configuration
@EnableWebSocketSecurity
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}

