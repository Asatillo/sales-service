package com.example.sales.service

import com.example.sales.feign.DeviceInterface
import com.example.sales.payload.PagedResponse
import com.example.sales.repository.SaleRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SaleService (
    val deviceInterface: DeviceInterface,
    val saleRepository: SaleRepository
    ) {

    fun getDeviceById(id: Long, authHeader: String): ResponseEntity<Any> {
        return ResponseEntity(deviceInterface.getById(authHeader, id).body!!, deviceInterface.getById(authHeader, id).statusCode)
    }

    fun getOrders(page: Int, size: Int, sort: String, search: String, authHeader: String): ResponseEntity<Any> {
        val pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort)
        val orders = saleRepository.findAll(pageable)
        val pagedResponse = PagedResponse(orders)
        for (order in pagedResponse.content) {

        }
    }
}