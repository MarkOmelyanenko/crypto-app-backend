package dev.march.financepetproject.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.march.financepetproject.dto.PriceUpdateDto;
import dev.march.financepetproject.entity.Asset;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PriceUpdateHandler extends AbstractWebSocketHandler {
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        System.out.println("✅ WebSocket Connected: " + session.getId());
        System.out.println("📡 Active Sessions: " + sessions.size());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        System.out.println("❌ WebSocket Disconnected: " + session.getId() + " Reason: " + status);
        System.out.println("📡 Active Sessions: " + sessions.size());
    }

    public void sendPriceUpdates(List<Asset> assets) throws IOException {
        cleanupClosedSessions();
        String json = new ObjectMapper().writeValueAsString(assets);
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(json));
        }
    }

    public void cleanupClosedSessions() {
        int beforeCleanup = sessions.size();
        sessions.removeIf(session -> !session.isOpen());
        System.out.println("🧹 Cleaned up closed sessions. Before: " + beforeCleanup + ", After: " + sessions.size());
    }
}
