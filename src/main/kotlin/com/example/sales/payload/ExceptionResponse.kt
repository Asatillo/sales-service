package com.example.sales.payload

import java.time.Instant

@Getter
class ExceptionResponse {
    private var error: String
    private var status: Int
    private var message: String
    private var validationErrors: HashMap<String, String> = HashMap()
    private var timestamp: Instant

    constructor(message: String, error: String, status: Int) {
        this.message = message
        this.error = error
        this.status = status
        timestamp = Instant.now()
    }

    constructor(message: String, error: String, status: Int,  validationErrors: HashMap<String, String>) {
        this.message = message
        this.error = error
        this.status = status
        this.validationErrors = validationErrors
        timestamp = Instant.now()
    }
}