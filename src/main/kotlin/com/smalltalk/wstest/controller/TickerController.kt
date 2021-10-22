package com.smalltalk.wstest.controller

import com.smalltalk.wstest.declaration.Logging
import com.smalltalk.wstest.error.MessageBroadcastError
import com.smalltalk.wstest.model.Ticker
import com.smalltalk.wstest.model.TickerRequest
import com.smalltalk.wstest.service.WebSocketScopedService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import java.security.Principal
import kotlin.random.Random

@Controller
class TickerController: Logging() {
    @Autowired lateinit var webSocketScopedService: WebSocketScopedService

    @MessageMapping("ticker")
    @SendTo("/topic/ticker")
    fun ticker(tickerRequest: TickerRequest?, @Headers headers: Map<String, String>, principal: Principal): Ticker {
        /*
         * null principal for anonymous user issue
         *      TickerRequest? works
         *      Principal? not works
         *          https://github.com/spring-projects/spring-security/issues/4011
         *          https://stackoverflow.com/questions/60288854/authentication-is-null-on-the-securitycontextholder-getcontext
         */
        log.debug("user[${principal.name}]messaged /app/ticker: $tickerRequest")
        Thread.sleep(1000L)

        webSocketScopedService.print()
        return Ticker(tickerRequest?.code ?: "xxx", Random.nextInt(990, 1010))
    }
}
