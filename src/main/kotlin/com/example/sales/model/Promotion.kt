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
import lombok.Data

@Data
@Entity
data class Promotion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long,

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private var name: String,

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 200, message = "Description must be less than 200 characters")
    private var description: String,

    @NotBlank(message = "Device cannot be blank")
    @Pattern(regexp = DEVICE_TYPES, message = "Device must be ")
    private var device: String,

    @NotNull(message = "Capital indicator cannot be blank")
    private var isForCapitalOnly: Boolean = true,

    @NotBlank(message = "Type cannot be blank")
    @Pattern(regexp = PROMOTIONS_TYPES, message = "Type must be either Percentage or Fixed")
    private var type: String,

    @NotBlank(message = "Amount cannot be blank")
    private var amount: Double,

    @NotNull(message = "Active indicator cannot be blank")
    private var active: Boolean = true
)