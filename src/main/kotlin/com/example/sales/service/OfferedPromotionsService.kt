package com.example.sales.service

import com.example.sales.exception.ResourceNotFoundException
import com.example.sales.model.OfferedPromotion
import com.example.sales.model.enums.ProductType
import com.example.sales.payload.ApiResponse
import com.example.sales.payload.PagedResponse
import com.example.sales.payload.requests.OfferedPromotionRequest
import com.example.sales.repository.OfferedPromotionsRepository
import com.example.sales.repository.PromotionRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OfferedPromotionsService(val offeredPromotionsRepository: OfferedPromotionsRepository,
                               val promotionRepository: PromotionRepository) {
    fun getAll(page: Int, size: Int, sort: String, search: String): PagedResponse<OfferedPromotion> {
        val pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort)
        val offeredPromotions = offeredPromotionsRepository.findAllWithSearch(search, pageable)
        return PagedResponse(offeredPromotions)
    }

    fun getById(id: Long): ResponseEntity<OfferedPromotion> {
        val offeredPromotion = offeredPromotionsRepository.findById(id).orElseThrow(){
            ResourceNotFoundException("OfferedPromotion", "id", id)
        }
        return ResponseEntity(offeredPromotion, HttpStatus.OK)
    }

    fun getByCustomerId(id: Long, page: Int, size: Int, sort: String, search: String): PagedResponse<OfferedPromotion> {
        val pageable : Pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort)
        val offeredPromotions = offeredPromotionsRepository.findByCustomerId(id, search, pageable)
        return PagedResponse(offeredPromotions)
    }

    fun getActiveByCustomerIdAndProductType(id: Long, type: ProductType, search: String): PagedResponse<OfferedPromotion> {
        val offeredPromotions = offeredPromotionsRepository.findByCustomerIdAndDeviceType(id, type, search)

        return PagedResponse(offeredPromotions, 1, offeredPromotions.size, offeredPromotions.size.toLong(), 1 )
    }

    fun add(offeredPromotion: OfferedPromotionRequest): ResponseEntity<OfferedPromotion> {
        val promotion = promotionRepository.findById(offeredPromotion.promotionId).orElseThrow(){
            ResourceNotFoundException("Promotion", "id", offeredPromotion.promotionId)
        }
        val newOfferedPromotion = OfferedPromotion(
            customerId =  offeredPromotion.customerId,
            promotion = promotion,
            communicationType =  offeredPromotion.communicationType,
            decision = offeredPromotion.decision,
            decisionDate = offeredPromotion.decisionDate
        )
        return ResponseEntity(offeredPromotionsRepository.save(newOfferedPromotion), HttpStatus.CREATED)
    }

    fun update(id: Long, offeredPromotion: OfferedPromotionRequest): ResponseEntity<OfferedPromotion> {
        val existingOfferedPromotion = offeredPromotionsRepository.findById(id).orElseThrow(){
            ResourceNotFoundException("OfferedPromotion", "id", id)
        }
        if(existingOfferedPromotion.customerId != offeredPromotion.customerId) {
            existingOfferedPromotion.customerId = offeredPromotion.customerId
        }

        if(existingOfferedPromotion.promotion.id != offeredPromotion.promotionId) {
            existingOfferedPromotion.promotion = promotionRepository.findById(offeredPromotion.promotionId).orElseThrow(){
                ResourceNotFoundException("Promotion", "id", offeredPromotion.promotionId)
            }
        }

        if(existingOfferedPromotion.communicationType != offeredPromotion.communicationType) {
            existingOfferedPromotion.communicationType = offeredPromotion.communicationType
        }

        if(existingOfferedPromotion.decision != offeredPromotion.decision) {
            existingOfferedPromotion.decision = offeredPromotion.decision
            existingOfferedPromotion.decisionDate = LocalDate.now()
        }

        return ResponseEntity(offeredPromotionsRepository.save(existingOfferedPromotion), HttpStatus.OK)
    }

    fun delete(id: Long): ApiResponse {
        val existingOfferedPromotion = offeredPromotionsRepository.findById(id).orElseThrow(){
            ResourceNotFoundException("OfferedPromotion", "id", id)
        }
        offeredPromotionsRepository.delete(existingOfferedPromotion)
        return ApiResponse(true, "OfferedPromotion deleted successfully", HttpStatus.ACCEPTED)
    }
}