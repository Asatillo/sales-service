package com.example.sales.model

import com.example.sales.model.enums.DiscountAmountType
import com.example.sales.model.enums.ProductType
import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
data class Discount(
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must be less than 50 characters")
    var name: String,

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 200, message = "Description must be less than 200 characters")
    var description: String,

    @NotNull(message = "Service types cannot be blank")
    @Enumerated(EnumType.STRING)
    var productTypes: ProductType,

    // percentage, fixed
    @NotNull(message = "Type cannot be blank")
    @Enumerated(EnumType.STRING)
    var type: DiscountAmountType,

    @NotNull(message = "Amount cannot be null")
    var amount: Double,

    // if percentage, what is the max amount of the discount that can be applied
    // if zero, then no max amount
    @NotNull(message = "Max amount cannot be null")
    @PositiveOrZero(message = "Max amount must be greater than or equal to zero")
    var maxAmount: Double,

    // if service type is plan or service, then what type of device is it for

    @NotBlank(message = "Type cannot be blank")
    var active: Boolean = true,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
)