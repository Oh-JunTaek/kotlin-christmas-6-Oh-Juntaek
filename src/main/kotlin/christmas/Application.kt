package christmas

import java.time.LocalDate
import java.time.Month
import java.time.Year

fun main() {
    println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    val inputView = InputView()
    val outputView = OutputView()
    val discount = DdayDiscount()
    val day = inputView.readDate()
    val year = Year.now().value
    val month = Month.DECEMBER.value
    val date: LocalDate = LocalDate.of(year, month, day)
    val order = inputView.readOrder(date)
    val totalDiscount = discount.calculateDiscount(order.date, order.totalAmount, order.isSpecialDay, order.dessertCount, order.mainCount)
    val finalAmount = order.totalAmount - totalDiscount

    outputView.printOrderSummary(order, discount, finalAmount)
}