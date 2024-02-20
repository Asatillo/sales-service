package com.example.sales.model

import com.example.sales.model.enums.AmountType
import com.example.sales.model.enums.ProductType
import com.example.sales.model.enums.PromotionType
import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.time.LocalDate

@Entity
data class Promotion(
    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(max = 50, message = "Name must be less than 50 characters")
    var name: String,

    @field:NotBlank(message = "Description cannot be blank")
    @field:Size(max = 200, message = "Description must be less than 200 characters")
    var description: String,

    @field:NotNull(message = "Type cannot be blank")
    @Enumerated(EnumType.STRING)
    var type: PromotionType,

    @field:NotNull(message = "Service types cannot be blank")
    @Enumerated(EnumType.STRING)
    var productType: ProductType,

    @field:NotNull(message = "Type cannot be blank")
    @Enumerated(EnumType.STRING)
    var amountType: AmountType,

    var startDate: LocalDate? = LocalDate.now(),

    @field:NotNull(message = "End date cannot be null")
    var endDate: LocalDate,

    var targetCustomerSegment: String? = null,

    @field:NotNull(message = "Amount cannot be null")
    @field:Positive(message = "Amount must be greater than zero")
    var amount: Double,

    @field:NotNull(message = "Max amount cannot be null")
    @field:PositiveOrZero(message = "Max amount must be greater than or equal to zero")
    var maxAmount: Double,

    @field:NotNull(message = "Renewable cannot be null")
    var renewable: Boolean,

    var active: Boolean = true,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {
    fun isExpired(): Boolean {
        return endDate.isBefore(LocalDate.now())
    }
}

//targetAudience: Customer segments eligible for the offer/promotion (e.g., new customers, specific plans)