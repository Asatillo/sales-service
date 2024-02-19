package com.example.sales.controller

import com.example.sales.model.Sale
import com.example.sales.payload.PagedResponse
import com.example.sales.payload.requests.SaleRequest
import com.example.sales.service.SaleService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("orders")
class SaleController (val saleService: SaleService){
    @GetMapping
    @Operation(summary = "Get all sales")
    fun getSales(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "sort", defaultValue = "id") sort: String,
        @RequestParam(value = "search", defaultValue = "") search: String,
    ): PagedResponse<Sale> {
        return saleService.getSales(page-1, size, sort, search)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get sale by id")
    fun getSaleById(@PathVariable id: Long): ResponseEntity<Sale> {
        return saleService.getSaleById(id)
    }

    @GetMapping("/customer/{id}")
    @Operation(summary = "Get sales by customer id")
    fun getSalesByCustomerId(
        @PathVariable id: Long,
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "sort", defaultValue = "id") sort: String,
        @RequestParam(value = "search", defaultValue = "") search: String,
    ): PagedResponse<Sale> {
        return saleService.getSalesByCustomerId(id, page -1 , size, sort, search)
    }

    @PostMapping
    @Operation(summary = "Add a sale")
    fun add(@RequestBody sale: SaleRequest): ResponseEntity<Sale> {
        return saleService.add(sale)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a sale")
    fun update(@PathVariable id: Long, @RequestBody sale: SaleRequest): ResponseEntity<Sale> {
        return saleService.update(id, sale)
    }
}