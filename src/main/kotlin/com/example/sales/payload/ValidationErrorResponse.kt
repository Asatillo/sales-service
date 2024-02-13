package com.example.sales.payload
import java.time.Instant

class ValidationErrorResponse(
    val message: String,
    val error: String,
    val status: Int,
    val validationErrors: HashMap<String, String>
) {
    val timestamp: Instant = Instant.now()

}
