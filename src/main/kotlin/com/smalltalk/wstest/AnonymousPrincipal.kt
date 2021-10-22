package com.smalltalk.wstest

import java.security.Principal

class AnonymousPrincipal {
    companion object: Principal {
        override fun getName() = "anonymous-principal-name"
    }
}