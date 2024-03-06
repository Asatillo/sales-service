package com.example.sales.repository

import com.example.sales.model.OfferedPromotion
import com.example.sales.model.enums.ProductType
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OfferedPromotionsRepository: JpaRepository<OfferedPromotion, Long> {
    fun findByCustomerId(id: Long, pageable: Pageable): Page<OfferedPromotion>

    @Query("SELECT o FROM OfferedPromotion o WHERE o.customerId = :id AND o.promotion.productType = :type " +
            "AND o.promotion.name LIKE %:search% and o.decision = 'ACCEPTED' and o.promotion.active = true")
    fun findByCustomerIdAndDeviceType(id: Long, type: ProductType, search: String): List<OfferedPromotion>

    @Query("SELECT o FROM OfferedPromotion o " +
            "WHERE CONCAT(o.promotion.name, ' ', o.decision, ' ', o.communicationType)  LIKE %:search%")
    fun findAllWithSearch(search: String, pageable: PageRequest): Page<OfferedPromotion>
}