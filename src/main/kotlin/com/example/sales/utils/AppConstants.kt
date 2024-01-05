package com.example.sales.utils

class AppConstants {
    companion object {
        const val DEFAULT_PAGE_NUMBER = 0
        const val DEFAULT_PAGE_SIZE = 30
        const val MAX_PAGE_SIZE = 50
        const val DEFAULT_SORT_BY = "id"
        const val SALE_ORDER_TYPE = "^(PLAN|PACKAGE|DEVICE|ACCESSORIES|ALL)$"
        const val PROMOTIONS_TYPES = "^(PERCENTAGE|FIXED)$"
        const val DEVICE_TYPES = "^(MOBILE|ROUTER)$"
        const val DECISION_TYPES = "^(ACCEPTED|REJECTED|PENDING)$"
        const val COMMUNICATION_TYPES = "^(SMS|EMAIL|CALL|IN-PERSON)$"
    }
}