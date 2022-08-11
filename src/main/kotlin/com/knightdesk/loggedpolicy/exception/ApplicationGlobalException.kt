package com.knightdesk.loggedpolicy.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ApplicationGlobalException: Exception() {

    @ExceptionHandler(EmptyListException::class)
    fun emptyList(ex: EmptyListException?): ResponseEntity<String?> {
        return ResponseEntity("No entry of any policy", HttpStatus.NO_CONTENT)
    }
}