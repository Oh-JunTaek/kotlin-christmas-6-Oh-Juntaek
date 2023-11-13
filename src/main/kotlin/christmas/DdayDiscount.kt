package christmas

import java.time.LocalDate

class DdayDiscount {

        companion object {
            const val START_DISCOUNT = 1000
            const val INCREASE_DISCOUNT = 100
            const val WEEKDAY_DESSERT_DISCOUNT = 2023
            const val WEEKEND_MAIN_DISCOUNT = 2023
            const val SPECIAL_DISCOUNT = 1000
            const val GIFT_THRESHOLD = 120000
            val START_DATE = LocalDate.of(2023, 12, 1)
            val END_DATE = LocalDate.of(2023, 12, 25)
            val WEEKENDS = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
        }
            val CHAMPAGNE_PRICE = MenuList.ALL_MENUS["샴페인"]?.price ?: 0
//각종 상수 선언

    fun calculateDiscount(
        date: LocalDate,
        totalAmount: Int,
        isSpecialDay: Boolean,
        dessertCount: Int,
        mainCount: Int
    ): Int {
        validateDate(date)
        var discount = START_DISCOUNT + (date.dayOfMonth - 1) * INCREASE_DISCOUNT

        if (!WEEKENDS.contains(date.dayOfMonth)) {
            discount += dessertCount * WEEKDAY_DESSERT_DISCOUNT
            return discount
        }

        discount += mainCount * WEEKEND_MAIN_DISCOUNT

        if (isSpecialDay) {
            discount += SPECIAL_DISCOUNT
        }

        if (totalAmount >= GIFT_THRESHOLD) {
            discount += CHAMPAGNE_PRICE
        }

        return discount
    }

        fun calculateFinalAmount(totalAmount: Int, totalDiscount: Int): Int {
            return totalAmount - totalDiscount
        }

        private fun validateDate(date: LocalDate) {
            if (date.isBefore(START_DATE) || date.isAfter(END_DATE)) {
                throw IllegalArgumentException("날짜는 ${START_DATE.year}년 ${START_DATE.monthValue}월 ${START_DATE.dayOfMonth}일부터 ${END_DATE.year}년 ${END_DATE.monthValue}월 ${END_DATE.dayOfMonth}일까지만 가능합니다.")
            }
        }
    }
