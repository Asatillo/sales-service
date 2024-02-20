package com.example.sales.model

import com.example.sales.model.enums.PaymentMethod
import com.example.sales.model.enums.PaymentProgress
import com.example.sales.model.enums.ProductType
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
class Sale(
    @ManyToOne
    @JoinColumn(name = "promotion_id")
    var promotion: Promotion,

    @field:NotBlank(message = "Description is required")
    var description: String,

    @field:NotNull(message = "Product ID is required")
    var productId: Long? = null,

    @field:NotNull(message = "Product type is required")
    @Enumerated(EnumType.STRING)
    var productType: ProductType? = null,

    @field:NotNull(message = "Customer ID is required")
    var customerId: Long,

    @field:NotBlank(message = "Customer is required")
    var customer: String,

    @field:NotNull(message = "Amount is required")
    @field:Min(value = 1, message = "Amount must be greater than 0")
    var amount: Double,

    @field:Min(value = 0, message = "Discount amount must be greater than or equal to 0")
    @field:NotNull(message = "Discount amount is required")
    var discountAmount: Double,

    @field:NotNull(message = "Total amount is required")
    var totalAmount: Double,

    var paymentMethod: PaymentMethod? = null,

    @Enumerated(EnumType.STRING)
    var paymentProgress: PaymentProgress? = PaymentProgress.PENDING,

    var paymentDate: LocalDate? = null,

    var createDate: LocalDate? = LocalDate.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)