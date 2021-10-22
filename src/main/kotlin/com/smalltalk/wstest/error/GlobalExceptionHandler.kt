package com.smalltalk.wstest.error

import com.smalltalk.wstest.declaration.Logging
import com.smalltalk.wstest.model.StockSummary
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.web.bind.annotation.ControllerAdvice

@ControllerAdvice
class GlobalExceptionHandler: Logging() {
    @MessageExceptionHandler
    @SendToUser(destinations= ["/queue/position-update"], broadcast=false)
    fun handleException(e: Exception): StockSummary {
        log.error("## error occurred: ", e)
        return StockSummary("## error: ${e.message ?: "empty error message"}", 999)
    }
}
