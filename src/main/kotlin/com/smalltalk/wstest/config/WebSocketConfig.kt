package com.smalltalk.wstest.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.util.AntPathMatcher
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry
            .setApplicationDestinationPrefixes("/app")
            .setPreservePublishOrder(true)
            .setPathMatcher(AntPathMatcher("."))
            .enableSimpleBroker("/topic", "/queue")
    }

    override fun configureWebSocketTransport(registration: WebSocketTransportRegistration) {
        /*
         * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp-configuration-performance
         * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/socket/config/annotation/WebSocketTransportRegistration.html
         */
        registration
            .setSendTimeLimit(1 * 1000)
            .setSendBufferSizeLimit(512 * 1024)
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/websocket").withSockJS()
    }
}
