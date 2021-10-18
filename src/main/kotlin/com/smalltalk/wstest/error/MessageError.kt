package com.smalltalk.wstest.error

sealed class MessageError

class MessageBroadcastError(val bar: String): MessageError()
class SomewhatError(val bar: Any): MessageError()