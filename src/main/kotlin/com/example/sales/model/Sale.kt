package com.example.sales.model

import com.example.sales.model.enums.PaymentMethod
import com.example.sales.model.enums.PaymentProgress
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate

@Entity
class Sale(
    @ManyToOne
    @JoinColumn(name = "promotion_id")
    var promotion: Promotion,

    var deviceId: Long? = null,

    var serviceId: Long? = null,

    var productId: Long? = null,

    @field:NotNull(message = "Customer ID is required")
    var customerId: Long,

    @field:NotNull(message = "Amount is required")
    @field:Min(value = 1, message = "Amount must be greater than 0")
    var amount: Double,

    @field:NotNull(message = "Total amount is required")
    var totalAmount: Double,

    @field:NotNull(message = "Payment method is required")
    @Enumerated(EnumType.STRING)
    var paymentMethod: PaymentMethod,

    @field:NotNull(message = "Payment progress is required")
    @Enumerated(EnumType.STRING)
    var paymentProgress: PaymentProgress? = PaymentProgress.PENDING,

    var paymentDate: LocalDate,

    @field:NotNull(message = "Create date is required")
    @CreatedDate
    var createDate: LocalDate,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)