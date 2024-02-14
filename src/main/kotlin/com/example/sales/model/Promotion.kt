package com.example.sales.model

import com.example.sales.model.enums.DiscountAmountType
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Entity
data class Promotion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(max = 50, message = "Name must be less than 50 characters")
    var name: String,

    @field:NotBlank(message = "Description cannot be blank")
    @field:Size(max = 200, message = "Description must be less than 200 characters")
    var description: String,

    @field:NotNull(message = "Capital indicator cannot be blank")
    var isForCapitalOnly: Boolean = true,

    @field:NotBlank(message = "Type cannot be blank")
    @Enumerated(EnumType.STRING)
    var type: DiscountAmountType,

    @field:NotBlank(message = "Amount cannot be blank")
    var amount: Double,

    @field:NotNull(message = "Active indicator cannot be blank")
    var active: Boolean = true
)