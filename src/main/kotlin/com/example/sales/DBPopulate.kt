package com.example.sales

import com.example.sales.model.Promotion
import com.example.sales.model.enums.DiscountAmountType
import com.example.sales.model.enums.ProductType
import com.example.sales.repository.PromotionRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DBPopulate(val promotionRepository: PromotionRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {

        val promotion1 = Promotion(
            name = "5% off",
            description = "5% off all services",
            productTypes = ProductType.PLAN,
            type = DiscountAmountType.PERCENTAGE,
            amount = 5.0,
            maxAmount = 10000.0,
        )
        val promotion2 = Promotion(
            name = "10% off",
            description = "10% off all devices",
            productTypes = ProductType.DEVICE,
            type = DiscountAmountType.PERCENTAGE,
            amount = 10.0,
            maxAmount = 10000.0,
        )
        val promotion3 = Promotion(
            name = "30% off",
            description = "10% off all plans",
            productTypes = ProductType.PLAN,
            type = DiscountAmountType.PERCENTAGE,
            amount = 30.0,
            maxAmount = 10000.0,
        )
        val promotion4 = Promotion(
            name = "10000 HUF",
            description = "10000 off all devices",
            productTypes = ProductType.DEVICE,
            type = DiscountAmountType.FIXED,
            amount = 10000.0,
            maxAmount = 10000.0,
        )
        val promotion5 = Promotion(
            name = "10000 HUF",
            description = "10000 off all plans",
            productTypes = ProductType.PLAN,
            type = DiscountAmountType.FIXED,
            amount = 10000.0,
            maxAmount = 10000.0,
        )
        promotionRepository.saveAll(listOf(promotion1, promotion2, promotion3, promotion4, promotion5))
    }

}