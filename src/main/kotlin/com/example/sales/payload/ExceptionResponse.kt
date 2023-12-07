package com.example.sales.payload

import lombok.Getter
import java.time.Instant

@Getter
class ExceptionResponse(
    private var error: String,
    private var status: Int,
    private var message: String,
    private var validationErrors: HashMap<String, String>,
    private var timestamp: Instant,
) {

    fun constructor(message: String, error: String, status: Int) {
        this.message = message
        this.error = error
        this.status = status
        this.timestamp = Instant.now()
    }

    fun constructor(message: String, error: String, status: Int, validationErrors: HashMap<String, String>) {
        this.message = message
        this.error = error
        this.status = status
        this.validationErrors = validationErrors
        this.timestamp = Instant.now()
    }

}