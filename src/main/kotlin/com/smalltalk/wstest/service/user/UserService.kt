package com.smalltalk.wstest.service.user

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService: UserDetailsService {

    companion object {
        private val encoder = BCryptPasswordEncoder()
        private fun user(user: String, password: String, vararg roles: String) =
            User.builder().username(user).password(encoder.encode(password)).roles(*roles).build()

        val USERS: Map<String, UserDetails> = mapOf(
            "ysl" to user("ysl", "ysl", "USER", "ADMIN"),
            "shc" to user("shc", "shc", "USER")
        )
    }

    override fun loadUserByUsername(name: String?): UserDetails? {
        return USERS[name]
    }
}
