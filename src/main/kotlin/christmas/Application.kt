package christmas

fun main() {
    println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    val inputView = InputView()
    val outputView = OutputView()
    val discount = DdayDiscount()
    val event = Event()

    val date = inputView.readDate()
    val order = inputView.readOrder(date)


    val badge = event.applyEvent(order)
    val totalDiscount = discount.calculateDiscount(order.date, order.totalAmount, order.isSpecialDay, order.dessertCount, order.mainCount)
    val finalAmount = order.totalAmount - totalDiscount

    outputView.printOrderSummary(order, discount, finalAmount)
//    outputView.printBadge(badge)
}