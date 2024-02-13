package com.example.sales.payload

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.http.HttpStatus

@JsonPropertyOrder(
    "success",
    "message"
)
class ApiResponse {
    @JsonPropertyOrder("success")
    var success: Boolean

    @JsonPropertyOrder("message")
    var message: String

    @JsonIgnore
    lateinit var status: HttpStatus

    constructor(success: Boolean, message: String){
        this.success = success
        this.message = message
    }

    constructor(success: Boolean, message: String, status: HttpStatus){
        this.success = success
        this.message = message
        this.status = status
    }
}