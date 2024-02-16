package com.example.sales.repository

import com.example.sales.model.Sale
import org.springframework.data.jpa.repository.JpaRepository

interface SaleRepository: JpaRepository<Sale, Long>