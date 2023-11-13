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
            val WEEKENDS = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30) }
            val specialDays = listOf(3, 10, 17, 24, 25, 31)
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
        if (totalAmount < Event.MIN_ORDER_AMOUNT) {
            return 0
        }
        var discount = calculateDdayDiscount(date.dayOfMonth)
        discount += calculateWeekdayDiscount(date.dayOfMonth, dessertCount)
        discount += calculateWeekendDiscount(date.dayOfMonth, mainCount)
        discount += calculateSpecialDiscount(isSpecialDay)
//        discount += calculateGiftEvent(totalAmount) 증정품 계산 빼기
        return discount
    }

    fun calculateFinalAmount(totalAmount: Int, totalDiscount: Int): Int {
        return totalAmount - totalDiscount
        }

    fun calculateDdayDiscount(dayOfMonth: Int): Int {
        return START_DISCOUNT + (dayOfMonth - 1) * INCREASE_DISCOUNT
    }

    fun calculateWeekdayDiscount(dayOfMonth: Int, dessertCount: Int): Int {
        return if (!WEEKENDS.contains(dayOfMonth)) dessertCount * WEEKDAY_DESSERT_DISCOUNT else 0
    }

    fun calculateWeekendDiscount(dayOfMonth: Int, mainCount: Int): Int {
        return if (WEEKENDS.contains(dayOfMonth)) mainCount * WEEKEND_MAIN_DISCOUNT else 0
    }

    fun calculateSpecialDiscount(isSpecialDay: Boolean): Int {
        return if (isSpecialDay) SPECIAL_DISCOUNT else 0
    }

    fun calculateGiftEvent(totalAmount: Int): Int {
        return if (totalAmount >= GIFT_THRESHOLD) CHAMPAGNE_PRICE else 0
    }
    fun calculateSpecialDayDiscount(day: Int): Int {
        return if (specialDays.contains(day)) SPECIAL_DISCOUNT else 0
    }

    private fun validateDate(date: LocalDate) {
        if (date.isBefore(START_DATE) || date.isAfter(END_DATE)) {
             throw IllegalArgumentException("날짜는 ${START_DATE.year}년 ${START_DATE.monthValue}월 ${START_DATE.dayOfMonth}일부터 ${END_DATE.year}년 ${END_DATE.monthValue}월 ${END_DATE.dayOfMonth}일까지만 가능합니다.")
        }
    }
}
