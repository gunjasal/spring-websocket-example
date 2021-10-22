package com.smalltalk.wstest.controller

import com.smalltalk.wstest.declaration.Logging
import com.smalltalk.wstest.model.StockSummary
import com.smalltalk.wstest.service.UserScopedService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.security.Principal

@Controller
class UserController: Logging() {
    @Autowired lateinit var userScopedService: UserScopedService

    @GetMapping(path = ["/", "/dashboard"])
    fun dashboard() = "/dashboard"

    @GetMapping("/login")
    fun login() = "/login"

    @PostMapping("/logout")
    fun logout() = "/logout"

    @MessageMapping("stock-updates")
    @SendToUser("/queue/position-updates")
    fun stockSummary(): StockSummary {
        log.debug("stock-updates called")
        return StockSummary("updated-stock", 123)
    }

    @MessageMapping("principal-message")
    fun principalMessage(principal: Principal) {
        log.debug("send principal message to [${principal.name}")
        userScopedService.send(principal.name)
    }
}