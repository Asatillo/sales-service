package com.example.sales.controller

import com.example.sales.model.OfferedPromotion
import com.example.sales.payload.ApiResponse
import com.example.sales.payload.PagedResponse
import com.example.sales.payload.requests.OfferedPromotionRequest
import com.example.sales.service.OfferedPromotionsService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("promotions/offered")
class OfferedPromotionsController(val offeredPromotionsService: OfferedPromotionsService) {

    @GetMapping
    fun getAll(
        @RequestParam(value = "page", required = false, defaultValue = "1") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
        @RequestParam(value = "sort", required = false, defaultValue = "id") sort: String,
        @RequestParam(value = "search", required = false, defaultValue = "") search: String
    ) : PagedResponse<OfferedPromotion> = offeredPromotionsService.getAll(page-1, size, sort, search)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<OfferedPromotion> = offeredPromotionsService.getById(id)

    @GetMapping("/customer/{id}")
    fun getByCustomerId(@PathVariable id: Long,
        @RequestParam(value = "page", required = false, defaultValue = "1") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int,
        @RequestParam(value = "sort", required = false, defaultValue = "id") sort: String,
        @RequestParam(value = "search", required = false, defaultValue = "") search: String
    ) : PagedResponse<OfferedPromotion>
    = offeredPromotionsService.getByCustomerId(id, page-1, size, sort, search)

    @PostMapping
    fun add(@Valid @RequestBody offeredPromotion: OfferedPromotionRequest) : ResponseEntity<OfferedPromotion>
    = offeredPromotionsService.add(offeredPromotion)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody offeredPromotion: OfferedPromotionRequest) : ResponseEntity<OfferedPromotion>
    = offeredPromotionsService.update(id, offeredPromotion)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ApiResponse = offeredPromotionsService.delete(id)
}
