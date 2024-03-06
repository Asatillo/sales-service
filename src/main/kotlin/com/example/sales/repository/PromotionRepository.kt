package com.example.sales.repository

import com.example.sales.model.Promotion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PromotionRepository: JpaRepository<Promotion, Long>{
    fun existsByName(name: String): Boolean

    @Query("SELECT p FROM Promotion p " +
            "WHERE CONCAT(p.name, ' ', p.description, ' ', p.productType, ' ', p.type) LIKE %:search%")
    fun findAllWithSearch(search: String, pageable: Pageable): Page<Promotion>
}