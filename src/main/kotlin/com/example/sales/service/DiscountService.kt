package com.example.sales.service

import com.example.sales.exception.ExistingResourceException
import com.example.sales.exception.ResourceNotFoundException
import com.example.sales.model.Discount
import com.example.sales.payload.ApiResponse
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
        val pageable : Pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort)
        val customers = discountRepository.findAll(pageable)
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

    fun updateDiscount(id: Long, discount: DiscountRequest): ResponseEntity<Discount> {
        val existingDiscount = discountRepository.findById(id).orElseThrow() {
            ResourceNotFoundException("Discount", "id", id)
        }
        if(existingDiscount.name != discount.name) {
            if(discountRepository.existsByName(discount.name)) {
                throw ExistingResourceException(ApiResponse(false, "Name already exists"))
            }
            existingDiscount.name = discount.name
        }
        if(existingDiscount.description != discount.description) {
            existingDiscount.description = discount.description
        }
        if(existingDiscount.productTypes != discount.productTypes) {
            existingDiscount.productTypes = discount.productTypes
        }
        if(existingDiscount.type != discount.type) {
            existingDiscount.type = discount.type
        }
        if(existingDiscount.amount != discount.amount) {
            existingDiscount.amount = discount.amount
        }
        if(existingDiscount.maxAmount != discount.maxAmount) {
            existingDiscount.maxAmount = discount.maxAmount
        }
        return ResponseEntity(discountRepository.save(existingDiscount), HttpStatus.OK)
    }

    fun updateDiscountStatus(id: Long, status: Boolean): ResponseEntity<Discount> {
        val discount = discountRepository.findById(id).orElseThrow() {
            ResourceNotFoundException("Discount", "id", id)
        }
        discount.active = status
        return ResponseEntity(discountRepository.save(discount), HttpStatus.OK)
    }
}