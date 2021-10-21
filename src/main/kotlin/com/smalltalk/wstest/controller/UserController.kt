package com.smalltalk.wstest.controller

import com.smalltalk.wstest.declaration.Logging
import com.smalltalk.wstest.model.StockSummary
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController: Logging() {
    @GetMapping(path = ["/", "/dashboard"])
    fun dashboard() = "/dashboard"

    @GetMapping("/login")
    fun login() = "/login"

    @PostMapping("/logout")
    fun logout() = "/logout"

    @MessageMapping("stock-updates")
    @SendToUser("/queue/position-updates")
    fun stockSummary(): List<StockSummary> {
        log.debug("ddd")
        return listOf(StockSummary("test", 123), StockSummary("test2", 456))
    }
}