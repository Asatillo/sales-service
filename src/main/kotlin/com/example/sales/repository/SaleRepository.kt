package com.example.sales.repository

import com.example.sales.model.Sale
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository

interface SaleRepository: JpaRepository<Sale, Long> {
    fun findByCustomerId(id: Long, pageable: PageRequest): Page<Sale>
}