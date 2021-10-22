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
    }

    override fun loadUserByUsername(name: String?): UserDetails? {
        return getUser(name) ?: throw RuntimeException("user not exist: $name")

    }

    fun getUser(name: String?): UserDetails? {
        // repository mock
        return mapOf(
            "ysl" to user("ysl", "ysl", "USER", "ADMIN"),
            "shc" to user("shc", "shc", "USER")
        )[name]
    }
}
