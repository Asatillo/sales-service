package com.example.sales.payload.requests

import com.example.sales.model.enums.DiscountAmountType
import com.example.sales.model.enums.ProductType
import jakarta.validation.constraints.NotNull

data class DiscountRequest (
    val name: String,
    val description: String,
    val productTypes: ProductType,
    val type: DiscountAmountType,
    @NotNull(message = "Amount cannot be null")
    val amount: Double,
    val maxAmount: Double
){
}