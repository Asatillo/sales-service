package com.example.sales.service

import com.example.sales.exception.ExistingResourceException
import com.example.sales.exception.ResourceNotFoundException
import com.example.sales.model.Promotion
import com.example.sales.payload.ApiResponse
import com.example.sales.payload.PagedResponse
import com.example.sales.payload.requests.PromotionRequest
import com.example.sales.repository.PromotionRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@Service
class PromotionService(val promotionRepository: PromotionRepository) {
    fun getPromotions(page: Int, size: Int, sort: String, search: String): PagedResponse<Promotion> {
        val pageable : Pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort)
        val customers = promotionRepository.findAll(pageable)
        return PagedResponse(customers)
    }

    fun addPromotion(promotion: PromotionRequest): ResponseEntity<Promotion> {
        return ResponseEntity(promotionRepository.save(promotion.getPromotion()), HttpStatus.CREATED)
    }

    fun getPromotion(id: Long): ResponseEntity<Promotion> {
        val promotion = promotionRepository.findById(id).orElseThrow() {
            ResourceNotFoundException("Promotion", "id", id)
        }
        return ResponseEntity(promotion, HttpStatus.OK)
    }

    fun updatePromotion(id: Long, promotion: PromotionRequest): ResponseEntity<Promotion> {
        val existingPromotion = promotionRepository.findById(id).orElseThrow() {
            ResourceNotFoundException("Promotion", "id", id)
        }
        if(existingPromotion.name != promotion.name) {
            if(promotionRepository.existsByName(promotion.name)) {
                throw ExistingResourceException(ApiResponse(false, "Name already exists"))
            }
            existingPromotion.name = promotion.name
        }
        if(existingPromotion.description != promotion.description) {
            existingPromotion.description = promotion.description
        }
        if(existingPromotion.productTypes != promotion.productTypes) {
            existingPromotion.productTypes = promotion.productTypes
        }
        if(existingPromotion.type != promotion.type) {
            existingPromotion.type = promotion.type
        }
        if(existingPromotion.amount != promotion.amount) {
            existingPromotion.amount = promotion.amount
        }
        if(existingPromotion.maxAmount != promotion.maxAmount) {
            existingPromotion.maxAmount = promotion.maxAmount
        }
        return ResponseEntity(promotionRepository.save(existingPromotion), HttpStatus.OK)
    }

    fun updatePromotionStatus(id: Long, status: Boolean): ResponseEntity<Promotion> {
        val promotion = promotionRepository.findById(id).orElseThrow() {
            ResourceNotFoundException("Promotion", "id", id)
        }
        promotion.active = status
        return ResponseEntity(promotionRepository.save(promotion), HttpStatus.OK)
    }
}