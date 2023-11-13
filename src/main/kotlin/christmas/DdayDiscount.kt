package christmas

import java.time.LocalDate

class DdayDiscount {
    companion object {
        const val START_DISCOUNT = 1000
        const val INCREASE_DISCOUNT = 100
        // 이벤트 시작 및 종료 날짜를 상수로 선언합니다.
        val START_DATE = LocalDate.of(2023, 12, 1)
        val END_DATE = LocalDate.of(2023, 12, 25)
    }
//이벤트 시작 및 종료 날짜를 LocalDate 객체로 선언

    fun calculateDiscount(date: LocalDate): Int {
        validateDate(date)
        return START_DISCOUNT + (date.dayOfMonth - 1) * INCREASE_DISCOUNT
    }

    private fun validateDate(date: LocalDate) {
        if (date.isBefore(START_DATE) || date.isAfter(END_DATE)) {
            throw IllegalArgumentException("날짜는 ${START_DATE.year}년 ${START_DATE.monthValue}월 ${START_DATE.dayOfMonth}일부터 ${END_DATE.year}년 ${END_DATE.monthValue}월 ${END_DATE.dayOfMonth}일까지만 가능합니다.")
        }
    }
}