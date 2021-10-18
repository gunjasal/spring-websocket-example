package com.smalltalk.wstest.task

import com.smalltalk.wstest.declaration.Logging
import com.smalltalk.wstest.model.Ticker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class TickerCrawler: Logging() {
    @Autowired lateinit var simpMessagingTemplate: SimpMessagingTemplate

    @Scheduled(fixedRate = 1000)
    @Async
    fun broadcast() {
        val ticker = Ticker("YSL", Random.nextInt(990, 1010))
        log.debug("${System.currentTimeMillis()/1000}: ${ticker}")

        simpMessagingTemplate.convertAndSend("/topic/ticker", ticker)
    }
}
