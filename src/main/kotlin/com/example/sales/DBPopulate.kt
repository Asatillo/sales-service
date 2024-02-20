package com.example.sales

import com.example.sales.model.OfferedPromotion
import com.example.sales.model.Promotion
import com.example.sales.model.Sale
import com.example.sales.model.enums.*
import com.example.sales.repository.OfferedPromotionsRepository
import com.example.sales.repository.PromotionRepository
import com.example.sales.repository.SaleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DBPopulate(val promotionRepository: PromotionRepository,
    val offeredPromotionsRepository: OfferedPromotionsRepository,
    val saleRepository: SaleRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {

        val promotion1 = Promotion(
            name = "5% off",
            description = "5% off all services",
            productTypes = ProductType.PLAN,
            type = PromotionType.DISCOUNT,
            amountType = AmountType.PERCENTAGE,
            amount = 5.0,
            maxAmount = 10000.0,
            renewable = false,
            endDate = LocalDate.now().plusDays(10)
        )
        val promotion2 = Promotion(
            name = "10% off",
            description = "10% off all devices",
            productTypes = ProductType.DEVICE,
            type = PromotionType.DISCOUNT,
            amountType = AmountType.PERCENTAGE,
            amount = 10.0,
            maxAmount = 10000.0,
            renewable = false,
            endDate = LocalDate.now().plusMonths(1)
        )
        val promotion3 = Promotion(
            name = "30% off",
            description = "10% off all plans",
            productTypes = ProductType.PLAN,
            type = PromotionType.DISCOUNT,
            amount = 30.0,
            amountType = AmountType.PERCENTAGE,
            maxAmount = 10000.0,
            endDate = LocalDate.now().plusMonths(2),
            renewable = false
        )
        val promotion4 = Promotion(
            name = "10000 HUF",
            description = "10000 off all devices",
            productTypes = ProductType.DEVICE,
            type = PromotionType.DISCOUNT,
            amountType = AmountType.FIXED,
            amount = 10000.0,
            maxAmount = 10000.0,
            endDate = LocalDate.now().plusYears(1),
            renewable = false
        )
        val promotion5 = Promotion(
            name = "10000 HUF",
            description = "10000 off all plans",
            productTypes = ProductType.PLAN,
            type = PromotionType.DISCOUNT,
            amountType = AmountType.FIXED,
            amount = 10000.0,
            maxAmount = 10000.0,
            endDate = LocalDate.now().plusYears(1),
            renewable = false
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

        val sale1 = Sale(
            promotion = promotion1,
            description = "Basic plan sale with 5% off",
            productId = 1,
            productType = ProductType.PLAN,
            customerId = 1,
            customer = "Alice Smith",
            amount = 1999.0,
            discountAmount = 99.95,
            totalAmount = 1899.05,
            paymentMethod = PaymentMethod.CASH,
            paymentProgress = PaymentProgress.PAID,
            paymentDate = LocalDate.now()
        )

        val sale2 = Sale(
            promotion = promotion2,
            description = "Basic device sale with 10% off",
            customerId = 2,
            customer = "Bob Johnson",
            productId = 2,
            productType = ProductType.DEVICE,
            amount = 200000.0,
            discountAmount = 20000.0,
            totalAmount = 180000.0,
            paymentProgress = PaymentProgress.PENDING
        )

        val sale3 = Sale(
            promotion = promotion3,
            description = "Ultimate plan sale with 30% off",
            customerId = 3,
            customer = "Eva Andersen",
            productId = 3,
            productType = ProductType.PLAN,
            amount = 5999.0,
            discountAmount = 1799.7,
            totalAmount = 3599.4,
            paymentProgress = PaymentProgress.CANCELED,
            paymentDate = LocalDate.now()
        )

        val sale4 = Sale(
            promotion = promotion4,
            description = "Basic device sale with 10000 HUF off",
            customerId = 4,
            customer = "David Brown",
            productId = 4,
            productType = ProductType.DEVICE,
            amount = 10000.0,
            discountAmount = 10000.0,
            totalAmount = 9000.0,
            paymentMethod = PaymentMethod.CARD,
            paymentProgress = PaymentProgress.PAID,
            paymentDate = LocalDate.now()
        )

        val sale5 = Sale(
            promotion = promotion5,
            description = "Basic service sale with 10000 HUF off",
            customerId = 5,
            customer = "Charlie Davis",
            productId = 5,
            productType = ProductType.SERVICE,
            amount = 10000.0,
            discountAmount = 1000.0,
            totalAmount = 9000.0,
            paymentMethod = PaymentMethod.CARD,
            paymentProgress = PaymentProgress.PAID,
            paymentDate = LocalDate.now()
        )
        val sale6 = Sale(
            promotion = promotion6,
            description = "Basic service sale with 1000 HUF off",
            customerId = 6,
            customer = "Frank Evans",
            productId = 6,
            productType = ProductType.SERVICE,
            amount = 10000.0,
            discountAmount = 1000.0,
            totalAmount = 9000.0,
            paymentMethod = PaymentMethod.CARD,
            paymentProgress = PaymentProgress.PAID,
            paymentDate = LocalDate.now()
        )
        val sale7 = Sale(
            promotion = promotion7,
            description = "Basic service sale with 10% off",
            customerId = 7,
            customer = "Grace Garcia",
            productId = 7,
            productType = ProductType.SERVICE,
            amount = 10000.0,
            discountAmount = 1000.0,
            totalAmount = 9000.0,
            paymentMethod = PaymentMethod.CARD,
            paymentProgress = PaymentProgress.PAID,
            paymentDate = LocalDate.now()
        )
        val sale8 = Sale(
            promotion = promotion8,
            description = "Basic service sale with free service",
            customerId = 8,
            customer = "Henry Hernandez",
            productId = 8,
            productType = ProductType.SERVICE,
            amount = 10000.0,
            discountAmount = 1000.0,
            totalAmount = 9000.0,
            paymentMethod = PaymentMethod.CARD,
            paymentProgress = PaymentProgress.PAID,
            paymentDate = LocalDate.now()
        )
        val sale9 = Sale(
            promotion = promotion9,
            description = "Basic device sale with free device",
            customerId = 9,
            customer = "Isabella Hill",
            productId = 9,
            productType = ProductType.DEVICE,
            amount = 10000.0,
            discountAmount = 1000.0,
            totalAmount = 9000.0,
            paymentMethod = PaymentMethod.CARD,
            paymentProgress = PaymentProgress.PAID,
            paymentDate = LocalDate.now()
        )
        val sale10 = Sale(
            promotion = promotion10,
            description = "Basic plan sale with free plan",
            customerId = 10,
            customer = "Jack Hughes",
            productId = 10,
            productType = ProductType.PLAN,
            amount = 10000.0,
            discountAmount = 1000.0,
            totalAmount = 9000.0,
            paymentMethod = PaymentMethod.CARD,
            paymentProgress = PaymentProgress.PAID,
            paymentDate = LocalDate.now()
        )

        saleRepository.saveAll(listOf(sale1, sale2, sale3, sale4, sale5, sale6, sale7, sale8, sale9, sale10))
    }

}