package com.example.sales.payload

import java.time.Instant

class ExceptionResponse(val message: String, val error: String, val status: Int) {
    val timestamp: Instant = Instant.now()
}