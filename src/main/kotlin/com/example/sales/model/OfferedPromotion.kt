package com.example.sales.model

import com.example.sales.model.enums.CommunicationType
import com.example.sales.model.enums.DecisionType
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedBy
import java.time.LocalDate

@Entity
data class OfferedPromotion(
    @field:NotNull(message = "Customer id cannot be blank")
    var customerId: Long,

    @field:NotNull(message = "Promotion id cannot be null")
    @ManyToOne
    @JoinColumn(name = "promotion_id")
    var promotion: Promotion,

    @field:NotNull(message = "Offer date cannot be blank")
    var offerDate: LocalDate? = LocalDate.now(),

    @field:NotNull(message = "Communication type cannot be empty")
    @Enumerated(EnumType.STRING)
    var communicationType: CommunicationType,

    @Enumerated(EnumType.STRING)
    @field:NotNull(message = "Decision type cannot be blank")
    var decision: DecisionType? = DecisionType.PENDING,

    var decisionDate: LocalDate? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
)