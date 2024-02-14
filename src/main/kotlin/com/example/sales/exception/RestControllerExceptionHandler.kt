package com.example.sales.exception

import com.example.sales.payload.ApiResponse
import com.example.sales.payload.ExceptionResponse
import com.example.sales.payload.ValidationErrorResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class RestControllerExceptionHandler {

    @ExceptionHandler(value = [ApiException::class, BadRequestException::class, ExistingResourceException::class,
            InvalidInputException::class, ReferencedRecordException::class, ResourceNotFoundException::class])
    @ResponseBody
    fun resolveException(exception: RuntimeException): ResponseEntity<ApiResponse> {
        val apiResponse = when (exception) {
            is ApiException -> exception.apiResponse
            is BadRequestException -> exception.apiResponse
            is ExistingResourceException -> exception.apiResponse
            is InvalidInputException -> exception.apiResponse
            is ReferencedRecordException -> exception.apiResponse
            is ResourceNotFoundException -> exception.apiResponse
            else -> throw IllegalStateException("Unexpected exception type: ${exception.javaClass}")
        }

        val status = when(exception) {
            is ApiException -> exception.status
            is BadRequestException -> exception.status
            is ExistingResourceException -> exception.status
            is InvalidInputException -> exception.status
            is ReferencedRecordException -> exception.status
            is ResourceNotFoundException -> exception.status
            else -> throw IllegalStateException("Unexpected exception type: ${exception.javaClass}")
        }

        return ResponseEntity(apiResponse, status)
    }

    @ExceptionHandler(value = [HttpRequestMethodNotSupportedException::class])
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    fun resolveException(exception: HttpRequestMethodNotSupportedException): ResponseEntity<ExceptionResponse> {
        val message = exception.supportedMethods?.let {
            String.format("Requested method %s is not supported for this endpoint. Allowed methods: %s",
                exception.method, it.joinToString(", "))
        }
        return ResponseEntity(message?.let {
            ExceptionResponse(
                it, HttpStatus.METHOD_NOT_ALLOWED.reasonPhrase,
                HttpStatus.METHOD_NOT_ALLOWED.value())
        }, HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    @ResponseBody
    fun resolveException(exception: MethodArgumentNotValidException): ResponseEntity<ValidationErrorResponse> {
        val validationErrors:HashMap<String, String> = HashMap()
        exception.bindingResult.fieldErrors.forEach { fieldError ->
            validationErrors[fieldError.field] = fieldError.defaultMessage!!
        }

        val message = if(validationErrors.size == 1)  "Validation error" else "Validation errors"
        return ResponseEntity(ValidationErrorResponse(message, HttpStatus.BAD_REQUEST.reasonPhrase,
            HttpStatus.BAD_REQUEST.value(), validationErrors), HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [DataIntegrityViolationException::class])
    @ResponseBody
    fun resolveException(exception: DataIntegrityViolationException): ResponseEntity<ExceptionResponse> {
        val message = exception.mostSpecificCause.message!!
        return ResponseEntity(ExceptionResponse(message, HttpStatus.BAD_REQUEST.reasonPhrase,
            HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST)
    }

    // TODO: is needed?
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MissingServletRequestParameterException::class])
    @ResponseBody
    fun resolveException(exception: MissingServletRequestParameterException): ResponseEntity<ExceptionResponse> {
        val message = String.format("Required parameter %s is not present", exception.parameterName)
        return ResponseEntity(ExceptionResponse(message, HttpStatus.BAD_REQUEST.reasonPhrase,
            HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    @ResponseBody
    fun resolveException(exception: HttpMessageNotReadableException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity(ExceptionResponse("Malformed JSON request", HttpStatus.BAD_REQUEST.reasonPhrase,
            HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST)
    }
}