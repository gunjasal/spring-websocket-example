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
import java.lang.RuntimeException
import kotlin.random.Random

@Controller
class TickerController: Logging() {
    @Autowired lateinit var webSocketScopedService: WebSocketScopedService

    @MessageMapping("ticker")
    @SendTo("/topic/ticker")
    fun ticker(tickerRequest: TickerRequest, @Headers headers: Map<String, String>): Ticker {
        log.debug("messaged /app/ticker: ${tickerRequest}")
        Thread.sleep(1000L)

        webSocketScopedService.print()
        return Ticker(tickerRequest.code, Random.nextInt(990, 1010))
    }

    // error handling test
    @MessageMapping("/error")
    fun exception() {
        throw RuntimeException("message error occurred")
    }

    @MessageExceptionHandler
    @SendToUser(destinations= ["/queue/errors"], broadcast=false)
    fun handleException(error: Error): MessageBroadcastError {
        return MessageBroadcastError("## error")
    }
}
