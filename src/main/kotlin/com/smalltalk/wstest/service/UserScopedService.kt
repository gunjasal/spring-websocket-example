package com.smalltalk.wstest.service

import com.smalltalk.wstest.declaration.Logging
import com.smalltalk.wstest.model.StockSummary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class UserScopedService: Logging() {
    @Autowired lateinit var simpMessagingTemplate: SimpMessagingTemplate

    fun send(name: String) {
        simpMessagingTemplate.convertAndSendToUser(
            name
            , "/queue/position-updates"
            , StockSummary("send to $name", 99999)
        )
    }
}
