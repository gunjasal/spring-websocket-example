package com.smalltalk.wstest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAsync
@EnableScheduling
@SpringBootApplication
class WstestApplication

fun main(args: Array<String>) {
	runApplication<WstestApplication>(*args)
}
