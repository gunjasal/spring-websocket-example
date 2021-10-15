package com.smalltalk.wstest.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {
    /*
     * STOMP messages whose destination headers start with /app may be routed to @MessageMapping methods in annotated controllers,
     *   while /topic and /queue messages may be routed directly to the message broker.
     */
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        // It also designates the /app prefix for messages that are bound for methods annotated with @MessageMapping.
        // This prefix will be used to define all the message mappings.
        // For example, /app/ticker is the endpoint that the TickerController.ticker() method is mapped to handle.
        registry.setApplicationDestinationPrefixes("/app")

        // enable a simple memory-based message broker to carry the greeting messages back to the client on destinations prefixed with /topic.
        registry.enableSimpleBroker("/topic", "/queue")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // The SockJS client will attempt to connect to /websocket and use the best available transport (websocket, xhr-streaming, xhr-polling, and so on).
        registry.addEndpoint("/websocket").withSockJS()
    }
}
