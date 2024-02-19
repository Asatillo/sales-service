package com.example.sales.payload.requests

import com.example.sales.model.Promotion
import com.example.sales.model.enums.AmountType
import com.example.sales.model.enums.ProductType
import com.example.sales.model.enums.PromotionType
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class PromotionRequest (
    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(max = 50, message = "Name must be less than 50 characters")
    var name: String,

    @field:NotBlank(message = "Description cannot be blank")
    @field:Size(max = 200, message = "Description must be less than 200 characters")
    var description: String,

    @field:NotNull(message = "Service types cannot be blank")
    @field:Enumerated(EnumType.STRING)
    var productTypes: ProductType,

    @field:NotNull(message = "Type cannot be blank")
    @field:Enumerated(EnumType.STRING)
    var type: PromotionType,

    @field:NotNull(message = "Amount type cannot be blank")
    @field:Enumerated(EnumType.STRING)
    var amountType: AmountType,

    var startDate: LocalDate? = LocalDate.now(),

    @field:NotNull(message = "End date cannot be null")
    var endDate: LocalDate,

    var targetCustomerSegment: String? = null,

    @field:NotNull(message = "Amount cannot be null")
    @field:Positive
    var amount: Double,

    @field:NotNull(message = "Max amount cannot be null")
    @field:PositiveOrZero
    var maxAmount: Double,

    @field:NotNull(message = "Renewable cannot be null")
    var renewable: Boolean

){
    fun getPromotion(): Promotion {
        return Promotion(name, description, type, productTypes, amountType, startDate, endDate, targetCustomerSegment,
            amount, maxAmount, renewable)
    }
}