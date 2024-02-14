package com.example.sales.service

import com.example.sales.exception.ResourceNotFoundException
import com.example.sales.model.Discount
import com.example.sales.payload.PagedResponse
import com.example.sales.payload.requests.DiscountRequest
import com.example.sales.repository.DiscountRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@Service
class DiscountService(val discountRepository: DiscountRepository) {
    fun getDiscounts(page: Int, size: Int, sort: String, search: String): PagedResponse<Discount> {
        var pageable : Pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort)
        var customers = discountRepository.findAll(pageable)
        return PagedResponse(customers)
    }

    fun addDiscount(discount: DiscountRequest): ResponseEntity<Discount> {
        println(discount.getDiscount())
        return ResponseEntity(discountRepository.save(discount.getDiscount()), HttpStatus.CREATED)
    }

    fun getDiscount(id: Long): ResponseEntity<Discount> {
        val discount = discountRepository.findById(id).orElseThrow() {
            ResourceNotFoundException("Discount", "id", id)
        }
        return ResponseEntity(discount, HttpStatus.OK)
    }
}