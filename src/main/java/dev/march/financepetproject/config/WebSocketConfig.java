package dev.march.financepetproject.config;

import dev.march.financepetproject.websocket.PriceUpdateHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final PriceUpdateHandler priceUpdateHandler;

    public WebSocketConfig(PriceUpdateHandler priceUpdateHandler) {
        this.priceUpdateHandler = priceUpdateHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(priceUpdateHandler, "/ws/prices")
                .setAllowedOrigins("*")
                .setAllowedOriginPatterns("*")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        System.out.println("🤝 Handshake success: WebSocket connection established");
                        return null;
                    }
                });
    }
}
