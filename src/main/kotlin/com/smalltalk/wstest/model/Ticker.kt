package com.smalltalk.wstest.model

import java.time.LocalDateTime

data class Ticker(val code: String, val price: Int, val datetime: LocalDateTime = LocalDateTime.now())
