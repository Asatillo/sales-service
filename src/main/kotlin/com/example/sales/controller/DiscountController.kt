package com.example.sales.controller

import com.example.sales.model.Discount
import com.example.sales.payload.PagedResponse
import com.example.sales.payload.requests.DiscountRequest
import com.example.sales.service.DiscountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("discounts")
class DiscountController(val discountService: DiscountService) {

    @GetMapping
    fun getDiscounts(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "sort", defaultValue = "id") sort: String,
        @RequestParam(value = "search", defaultValue = "") search: String
    ): PagedResponse<Discount> {
        return discountService.getDiscounts(page-1, size, sort, search)
    }

    @GetMapping("/{id}")
    fun getDiscount(@PathVariable id: Long): ResponseEntity<Discount> {
        return discountService.getDiscount(id)
    }

    @PostMapping
    fun addDiscount(discount: DiscountRequest): ResponseEntity<Discount> {
        return discountService.addDiscount(discount)
    }

}