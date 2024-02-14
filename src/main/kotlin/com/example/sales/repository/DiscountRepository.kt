package com.example.sales.repository

import com.example.sales.model.Discount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DiscountRepository: JpaRepository<Discount, Long>{
    fun existsByName(name: String): Boolean
}