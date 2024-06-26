package com.example.sales.exception

import com.example.sales.payload.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class ReferencedRecordException(
    val apiResponse: ApiResponse,
    val status: HttpStatus = HttpStatus.CONFLICT) : RuntimeException() {

    constructor(recordTable: String, fieldValue: Any, referenceTable: String) : this(ApiResponse(false, String.format("%s record with value '%s' has a reference in %s table", recordTable, fieldValue, referenceTable)))
}