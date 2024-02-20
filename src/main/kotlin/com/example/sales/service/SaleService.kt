package com.example.sales.service

import com.example.sales.exception.InvalidInputException
import com.example.sales.exception.ResourceNotFoundException
import com.example.sales.model.Promotion
import com.example.sales.model.Sale
import com.example.sales.model.enums.PaymentProgress
import com.example.sales.payload.ApiResponse
import com.example.sales.payload.PagedResponse
import com.example.sales.payload.requests.SaleRequest
import com.example.sales.repository.PromotionRepository
import com.example.sales.repository.SaleRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class SaleService (
    val saleRepository: SaleRepository,
    val promotionRepository: PromotionRepository
    ) {

    fun getSales(page: Int, size: Int, sort: String, search: String): PagedResponse<Sale> {
        val pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort)
        val orders = saleRepository.findAll(pageable)
        return PagedResponse(orders)
    }

    fun getSaleById(id: Long): ResponseEntity<Sale> {
        val sale = saleRepository.findById(id).orElseThrow() {
            ResourceNotFoundException("Sale", "id", id)
        }
        return ResponseEntity(sale, HttpStatus.OK)
    }

    fun getSalesByCustomerId(id: Long, page: Int, size: Int, sort: String, search: String): PagedResponse<Sale> {
        val pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort)
        val orders = saleRepository.findByCustomerId(id, pageable)
        return PagedResponse(orders)
    }

    fun add(sale: SaleRequest): ResponseEntity<Sale> {
        var promotion : Promotion? = null
        if(sale.promotionId != null) {
            promotion = promotionRepository.findById(sale.promotionId!!).orElseThrow() {
                ResourceNotFoundException("Promotion", "id", sale.promotionId!!)
            }
        }

        return ResponseEntity(saleRepository.save(Sale(
            promotion = promotion!!,
            description = sale.description,
            productId = sale.productId,
            productType = sale.productType,
            customerId = sale.customerId,
            customer = sale.customer,
            amount = sale.amount,
            discountAmount = sale.discountAmount,
            totalAmount = sale.totalAmount,
            paymentMethod = sale.paymentMethod,
            paymentProgress = sale.paymentProgress,
            paymentDate = sale.paymentDate
        )), HttpStatus.CREATED)
    }

    // unable to change the customer
    fun update(id: Long, sale: SaleRequest): ResponseEntity<Sale> {
        val promotion = promotionRepository.findById(sale.promotionId!!).orElseThrow() {
            ResourceNotFoundException("Promotion", "id", sale.promotionId!!)
        }

        val saleToUpdate = saleRepository.findById(id).orElseThrow() {
            ResourceNotFoundException("Sale", "id", id)
        }

        if(saleToUpdate.paymentProgress == PaymentProgress.PAID || saleToUpdate.paymentProgress == PaymentProgress
            .CANCELED) {
            throw InvalidInputException(ApiResponse(false, "Sale already paid"), HttpStatus.BAD_REQUEST)
        }else if(sale.paymentProgress != saleToUpdate.paymentProgress) {
            saleToUpdate.paymentProgress = sale.paymentProgress
            if(saleToUpdate.paymentProgress == PaymentProgress.PAID || saleToUpdate.paymentProgress == PaymentProgress
                .CANCELED) {
                saleToUpdate.paymentDate = sale.paymentDate
            }
        }

        if(saleToUpdate.promotion != promotion && !promotion.isExpired()) {
            saleToUpdate.promotion = promotion
        }

        if(saleToUpdate.productId != sale.productId) {
            saleToUpdate.productId = sale.productId
        }

        if(saleToUpdate.description != sale.description) {
            saleToUpdate.description = sale.description
        }

        if(saleToUpdate.productType != sale.productType) {
            saleToUpdate.productType = sale.productType
        }

        if(saleToUpdate.amount != sale.amount) {
            saleToUpdate.amount = sale.amount
        }

        if(saleToUpdate.discountAmount != sale.discountAmount) {
            saleToUpdate.discountAmount = sale.discountAmount
        }

        if(saleToUpdate.totalAmount != sale.totalAmount) {
            saleToUpdate.totalAmount = sale.totalAmount
        }

        if(saleToUpdate.amount - saleToUpdate.discountAmount != sale.totalAmount) {
            throw InvalidInputException(ApiResponse(false, "Total amount is not correct"), HttpStatus.BAD_REQUEST)
        }

        if(saleToUpdate.paymentMethod != sale.paymentMethod) {
            saleToUpdate.paymentMethod = sale.paymentMethod
        }

        return ResponseEntity(saleRepository.save(saleToUpdate), HttpStatus.OK)
    }
}