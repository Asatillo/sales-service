package com.example.sales.payload.requests

import com.example.sales.model.enums.CommunicationType
import com.example.sales.model.enums.DecisionType
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

data class OfferedPromotionRequest(
    @field:NotNull(message = "Customer id cannot be blank")
    var customerId: Long,

    @field:NotNull(message = "Promotion id cannot be null")
    var promotionId: Long,

    @field:NotNull(message = "Communication type cannot be empty")
    @Enumerated(EnumType.STRING)
    var communicationType: CommunicationType,

    @Enumerated(EnumType.STRING)
    @field:NotNull(message = "Decision type cannot be blank")
    var decision: DecisionType? = DecisionType.PENDING,
)