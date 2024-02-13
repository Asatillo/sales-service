package com.example.sales.exception

import com.example.sales.payload.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class ExistingResourceException(
    val apiResponse: ApiResponse,
    val status: HttpStatus = HttpStatus.CONFLICT) : RuntimeException() {
}