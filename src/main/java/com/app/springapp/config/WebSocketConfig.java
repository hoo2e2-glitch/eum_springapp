package com.app.springapp.config;

import com.app.springapp.handler.ChatWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatWebSocketHandler chatWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 클라이언트 연결 URL: ws://localhost:10000/ws/chat/{chatRoomId}
        registry.addHandler(chatWebSocketHandler, "/ws/chat/**")
                .setAllowedOrigins("http://localhost:3000");
    }
}