package com.example.sales.controller

import com.example.sales.exception.BadRequestException
import com.example.sales.model.Promotion
import com.example.sales.payload.ApiResponse
import com.example.sales.payload.PagedResponse
import com.example.sales.payload.requests.PromotionRequest
import com.example.sales.service.PromotionService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("promotions")
class PromotionController(val promotionService: PromotionService) {

    @GetMapping
    fun getPromotions(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "sort", defaultValue = "id") sort: String,
        @RequestParam(value = "search", defaultValue = "") search: String
    ): PagedResponse<Promotion> {
        return promotionService.getPromotions(page-1, size, sort, search)
    }

    @GetMapping("/{id}")
    fun getPromotion(@PathVariable id: Long): ResponseEntity<Promotion> {
        return promotionService.getPromotion(id)
    }

    @PostMapping
    fun addPromotion(@Valid @RequestBody promotion: PromotionRequest): ResponseEntity<Promotion> {
        return promotionService.addPromotion(promotion)
    }

    @PutMapping("/{id}")
    fun updatePromotion(@PathVariable id: Long, @Valid @RequestBody promotion: PromotionRequest): ResponseEntity<Promotion> {
        return promotionService.updatePromotion(id, promotion)
    }

    @PatchMapping("/{id}/{status}")
    fun updatePromotionStatus(@PathVariable id: Long, @PathVariable status: String): ResponseEntity<Promotion> {
        val booleanStatus = if(status == "activate") true else if(status == "deactivate") false else throw BadRequestException(
            ApiResponse(false, "Invalid status")
        )
        return promotionService.updatePromotionStatus(id, booleanStatus)
    }

}