package com.example.sales.repository

import com.example.sales.model.Sale
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SaleRepository: JpaRepository<Sale, Long> {
    fun findByCustomerId(id: Long, pageable: PageRequest): Page<Sale>

    @Query("SELECT s FROM Sale s WHERE CONCAT(s.description, ' ', s.customer, ' ', s.productType, ' ', s.paymentProgress) LIKE %:search%")
    fun findAllWithSearch(search: String, pageable: PageRequest): Page<Sale>
}