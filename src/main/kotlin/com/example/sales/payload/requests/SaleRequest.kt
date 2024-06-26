package com.example.sales.payload.requests

import com.example.sales.model.enums.PaymentMethod
import com.example.sales.model.enums.PaymentProgress
import com.example.sales.model.enums.ProductType
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

class SaleRequest(
    var promotionId: Long? = null,

    @field:NotNull(message = "Description is required")
    var description: String,

    @field:NotNull(message = "Product ID is required")
    var productId: Long,

    @field:NotNull(message = "Product type is required")
    @Enumerated(EnumType.STRING)
    var productType: ProductType,

    @field:NotNull(message = "Customer ID is required")
    var customerId: Long,

    @field:NotBlank(message = "Customer is required")
    var customer: String,

    @field:NotNull(message = "Amount is required")
    @field:Min(value = 0, message = "Amount must be greater than or equal to 0")
    var amount: Double,

    @field:Min(value = 0, message = "Discount amount must be greater than or equal to 0")
    var discountAmount: Double = 0.0,

    var totalAmount: Double,

    @Enumerated(EnumType.STRING)
    var paymentMethod: PaymentMethod? = null,

    @Enumerated(EnumType.STRING)
    var paymentProgress: PaymentProgress? = PaymentProgress.PENDING,

    var paymentDate: LocalDate? = null,
)