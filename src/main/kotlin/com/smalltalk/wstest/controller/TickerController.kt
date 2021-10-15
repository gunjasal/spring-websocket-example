package com.smalltalk.wstest.controller

import com.smalltalk.wstest.model.Ticker
import com.smalltalk.wstest.model.TickerRequest
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import kotlin.random.Random

@Controller
class TickerController {
    /*
     * The @MessageMapping annotation ensures that, if a message is sent to the /ticker destination, the ticker() method is called.
     * The return value is broadcast to all subscribers of /topic/greetings, as specified in the @SendTo annotation.
     */
    @MessageMapping("/ticker")
    @SendTo("/topic/ticker")
    fun ticker(tickerRequest: TickerRequest): Ticker {
        Thread.sleep(1000L)
        return Ticker(tickerRequest.code, Random.nextInt(990, 1010))
    }
}
