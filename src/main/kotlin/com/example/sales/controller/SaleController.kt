package com.example.sales.controller

import com.example.sales.service.SaleService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("orders")
class SaleController (val saleService: SaleService){
    @GetMapping("/{id}")
    fun getDevice(@PathVariable id: Long, @RequestHeader(HttpHeaders.AUTHORIZATION) authHeader: String): ResponseEntity<Any> {
        return saleService.getDeviceById(id, authHeader)
    }

    @GetMapping
    fun getOrders(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "sort", defaultValue = "id") sort: String,
        @RequestParam(value = "search", defaultValue = "") search: String,
        @RequestHeader(HttpHeaders.AUTHORIZATION) authHeader: String
    ): ResponseEntity<Any> {
        return saleService.getOrders(page, size, sort, search, authHeader)
    }
}