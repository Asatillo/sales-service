package com.example.sales.repository

import com.example.sales.model.Promotion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PromotionRepository: JpaRepository<Promotion, Long>{
    fun existsByName(name: String): Boolean
}