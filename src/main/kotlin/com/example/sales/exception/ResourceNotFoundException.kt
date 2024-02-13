package com.example.sales.exception

import com.example.sales.payload.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(
    var apiResponse: ApiResponse,
    var status: HttpStatus): RuntimeException() {

        init {
            status = HttpStatus.NOT_FOUND
        }


}