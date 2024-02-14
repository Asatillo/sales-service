package com.example.sales.payload.requests

import com.example.sales.model.Discount
import com.example.sales.model.enums.DiscountAmountType
import com.example.sales.model.enums.ProductType
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size

data class DiscountRequest (
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
    var type: DiscountAmountType,

    @field:NotNull(message = "Amount cannot be null")
    @field:Positive
    var amount: Double,

    @field:NotNull(message = "Max amount cannot be null")
    @field:PositiveOrZero
    var maxAmount: Double

){
    fun getDiscount(): Discount {
        return Discount(name, description, productTypes, type, amount, maxAmount)
    }
}