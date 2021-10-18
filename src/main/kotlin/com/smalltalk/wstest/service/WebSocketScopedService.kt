package com.smalltalk.wstest.service

import com.smalltalk.wstest.declaration.Logging
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
@Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
class WebSocketScopedService: Logging() {
    @PostConstruct
    fun init() {
        log.info("### websocket scoped bean init - $this")
    }

    fun print() {
        log.info("### websocket scoped bean prints - $this")
    }
    @PreDestroy
    fun destroy() {
        log.info("### websocket scoped bean destroyed - $this")
    }
}