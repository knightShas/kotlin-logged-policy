package com.knightdesk.loggedpolicy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoggedPolicyApplication

fun main(args: Array<String>) {
	runApplication<LoggedPolicyApplication>(*args)
}
