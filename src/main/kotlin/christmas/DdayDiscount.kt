package christmas

import java.time.LocalDate

class DdayDiscount {
    companion object {
        const val START_DISCOUNT = 1000
        const val INCREASE_DISCOUNT = 100
    }
//할인 금액 상수 선언

    private val startDate = LocalDate.of(2023, 12, 1)
    private val endDate = LocalDate.of(2023, 12, 25)
//이벤트 시작 및 종료 날짜를 LocalDate 객체로 선언
    fun calculateDiscount(date: LocalDate): Int {
        validateDate(date)
        return START_DISCOUNT + (date.dayOfMonth - 1) * INCREASE_DISCOUNT
    }
//입력받은 날짜 확인 후 할인금액 계산
    private fun validateDate(date: LocalDate) {
        if (date.isBefore(startDate) || date.isAfter(endDate)) {
            throw IllegalArgumentException("날짜는 2023년 12월 1일부터 2023년 12월 25일까지만 가능합니다.")
        }
    }
}
//날짜 유효성 검사