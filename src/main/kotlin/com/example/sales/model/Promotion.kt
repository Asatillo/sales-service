package com.example.sales.model

import com.example.sales.utils.AppConstants.Companion.DEVICE_TYPES
import com.example.sales.utils.AppConstants.Companion.PROMOTIONS_TYPES
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Entity
data class Promotion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must be less than 50 characters")
    var name: String,

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 200, message = "Description must be less than 200 characters")
    var description: String,

    @NotNull(message = "Capital indicator cannot be blank")
    var isForCapitalOnly: Boolean = true,

    @NotBlank(message = "Type cannot be blank")
    @Pattern(regexp = PROMOTIONS_TYPES, message = "Type must be either Percentage or Fixed")
    var type: String,

    @NotBlank(message = "Amount cannot be blank")
    var amount: Double,

    @NotNull(message = "Active indicator cannot be blank")
    var active: Boolean = true
)