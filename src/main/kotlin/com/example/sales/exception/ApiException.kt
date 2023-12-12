package com.example.sales.exception

import com.example.sales.payload.ApiResponse
import org.springframework.http.HttpStatus

class ApiException(
    private var apiResponse: ApiResponse,
    private var status: HttpStatus = HttpStatus.BAD_REQUEST): RuntimeException() {

    constructor(message: String) : this(ApiResponse(false, message))

    fun getApiResponse(): ApiResponse {
        return apiResponse
    }

    fun getStatus(): HttpStatus {
        return status
    }
}