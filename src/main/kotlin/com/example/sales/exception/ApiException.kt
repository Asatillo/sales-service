package com.example.sales.exception

import com.example.sales.payload.ApiResponse
import org.springframework.http.HttpStatus

class ApiException(
    var apiResponse: ApiResponse,
    var status: HttpStatus = HttpStatus.BAD_REQUEST): RuntimeException() {

    constructor(message: String) : this(ApiResponse(false, message))
}