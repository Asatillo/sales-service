package com.example.sales.exception

import com.example.sales.payload.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException(
    private val apiResponse: ApiResponse,
    private val status: HttpStatus = HttpStatus.BAD_REQUEST) : RuntimeException() {
}