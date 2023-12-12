package com.example.sales.exception

import com.example.sales.payload.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class ExistingResourceException(
    private val apiResponse: ApiResponse,
    private val status: HttpStatus = HttpStatus.CONFLICT) : RuntimeException() {

    fun getApiResponse(): ApiResponse {
        return apiResponse
    }

    fun getStatus(): HttpStatus {
        return status
    }
}