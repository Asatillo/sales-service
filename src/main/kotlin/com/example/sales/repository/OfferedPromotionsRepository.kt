package com.example.sales.repository

import com.example.sales.model.OfferedPromotion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OfferedPromotionsRepository: JpaRepository<OfferedPromotion, Long> {
    fun findByCustomerId(id: Long, pageable: Pageable): Page<OfferedPromotion>
}