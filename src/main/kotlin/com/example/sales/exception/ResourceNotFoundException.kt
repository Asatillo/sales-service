package com.example.sales.exception

import com.example.sales.payload.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(
    private val apiResponse: ApiResponse,
    private val status: HttpStatus = HttpStatus.NOT_FOUND): RuntimeException() {

    constructor(resourceName: String, fieldName: String, fieldValue: Any) : this(ApiResponse(false, String.format("%s with %s '%s' not found", resourceName, fieldName, fieldValue)))


}