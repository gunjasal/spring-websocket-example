package com.smalltalk.wstest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WstestApplication

fun main(args: Array<String>) {
	runApplication<WstestApplication>(*args)
}
