package christmas

import java.time.LocalDate


fun main() {
    println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")

    val inputView = InputView()
    val outputView = OutputView()
    val discount = DdayDiscount()
    val event = Event()

    var date: LocalDate? = null
    var order: Order? = null

    while (date == null) {
        try {
            date = inputView.readDate()
        } catch (e: IllegalArgumentException) {
            println("[ERROR] ${e.message}")
        }
    }

    while (order == null) {
        try {
            order = inputView.readOrder()
            order.printOrder()
        } catch (e: IllegalArgumentException) {
            println("[ERROR] ${e.message}")
        }
    }

    val badge = event.applyEvent(order)
    val totalDiscount = discount.calculateDiscount(order.date, order.totalAmount, order.isSpecialDay, order.dessertCount, order.mainCount)
    val finalAmount = order.totalAmount - totalDiscount

    outputView.printOrderSummary(order, discount, finalAmount)
    outputView.printBadge(badge)
}