package com.example.sales

import com.example.sales.model.Discount
import com.example.sales.model.enums.DiscountAmountType
import com.example.sales.model.enums.ProductType
import com.example.sales.repository.DiscountRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DBPopulate(val discountRepository: DiscountRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {

        val discount1 = Discount(
            name = "5% off",
            description = "5% off all services",
            productTypes = ProductType.PLAN,
            type = DiscountAmountType.PERCENTAGE,
            amount = 5.0,
            maxAmount = 10000.0,
        )
        val discount2 = Discount(
            name = "10% off",
            description = "10% off all devices",
            productTypes = ProductType.DEVICE,
            type = DiscountAmountType.PERCENTAGE,
            amount = 10.0,
            maxAmount = 10000.0,
        )
        val discount3 = Discount(
            name = "30% off",
            description = "10% off all plans",
            productTypes = ProductType.PLAN,
            type = DiscountAmountType.PERCENTAGE,
            amount = 30.0,
            maxAmount = 10000.0,
        )
        val discount4 = Discount(
            name = "10000 HUF",
            description = "10000 off all devices",
            productTypes = ProductType.DEVICE,
            type = DiscountAmountType.FIXED,
            amount = 10000.0,
            maxAmount = 10000.0,
        )
        val discount5 = Discount(
            name = "10000 HUF",
            description = "10000 off all plans",
            productTypes = ProductType.PLAN,
            type = DiscountAmountType.FIXED,
            amount = 10000.0,
            maxAmount = 10000.0,
        )
        discountRepository.saveAll(listOf(discount1, discount2, discount3, discount4, discount5))
    }

}