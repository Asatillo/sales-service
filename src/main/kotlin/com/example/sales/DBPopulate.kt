package com.example.sales

import com.example.sales.model.OfferedPromotion
import com.example.sales.model.Promotion
import com.example.sales.model.enums.CommunicationType
import com.example.sales.model.enums.AmountType
import com.example.sales.model.enums.ProductType
import com.example.sales.model.enums.PromotionType
import com.example.sales.repository.OfferedPromotionsRepository
import com.example.sales.repository.PromotionRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DBPopulate(val promotionRepository: PromotionRepository,
    val offeredPromotionsRepository: OfferedPromotionsRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {

        val promotion1 = Promotion(
            name = "5% off",
            description = "5% off all services",
            productTypes = ProductType.PLAN,
            type = PromotionType.DISCOUNT,
            amountType = AmountType.PERCENTAGE,
            amount = 5.0,
            maxAmount = 10000.0,
        )
        val promotion2 = Promotion(
            name = "10% off",
            description = "10% off all devices",
            productTypes = ProductType.DEVICE,
            type = PromotionType.DISCOUNT,
            amountType = AmountType.PERCENTAGE,
            amount = 10.0,
            maxAmount = 10000.0,
        )
        val promotion3 = Promotion(
            name = "30% off",
            description = "10% off all plans",
            productTypes = ProductType.PLAN,
            type = PromotionType.DISCOUNT,
            amount = 30.0,
            amountType = AmountType.PERCENTAGE,
            maxAmount = 10000.0,
        )
        val promotion4 = Promotion(
            name = "10000 HUF",
            description = "10000 off all devices",
            productTypes = ProductType.DEVICE,
            type = PromotionType.DISCOUNT,
            amountType = AmountType.FIXED,
            amount = 10000.0,
            maxAmount = 10000.0,
        )
        val promotion5 = Promotion(
            name = "10000 HUF",
            description = "10000 off all plans",
            productTypes = ProductType.PLAN,
            type = PromotionType.DISCOUNT,
            amountType = AmountType.FIXED,
            amount = 10000.0,
            maxAmount = 10000.0,
        )
        promotionRepository.saveAll(listOf(promotion1, promotion2, promotion3, promotion4, promotion5))

        val offeredPromotion1 = OfferedPromotion(
            customerId = 1,
            promotion = promotion1,
            offerDate = LocalDate.now(),
            communicationType = CommunicationType.EMAIL
        )

        val offeredPromotion2 = OfferedPromotion(
            customerId = 2,
            promotion = promotion2,
            offerDate = LocalDate.now(),
            communicationType = CommunicationType.SMS
        )

        val offeredPromotion3 = OfferedPromotion(
            customerId = 3,
            promotion = promotion3,
            offerDate = LocalDate.now(),
            communicationType = CommunicationType.EMAIL
        )

        val offeredPromotion4 = OfferedPromotion(
            customerId = 4,
            promotion = promotion4,
            offerDate = LocalDate.now(),
            communicationType = CommunicationType.SMS
        )

        val offeredPromotion5 = OfferedPromotion(
            customerId = 5,
            promotion = promotion5,
            offerDate = LocalDate.now(),
            communicationType = CommunicationType.EMAIL
        )

        offeredPromotionsRepository.saveAll(listOf(offeredPromotion1, offeredPromotion2, offeredPromotion3, offeredPromotion4, offeredPromotion5))
    }

}