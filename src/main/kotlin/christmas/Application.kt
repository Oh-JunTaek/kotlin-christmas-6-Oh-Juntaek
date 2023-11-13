package christmas


fun main() {
    println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")

    val inputView = InputView()
    val outputView = OutputView()
    val discount = DdayDiscount()
    val event = Event()

    try {
        val date = inputView.readDate()
        val order = inputView.readOrder()
        order.printOrder()

        val badge = event.applyEvent(order)
        val totalDiscount = discount.calculateDiscount(order.date, order.totalAmount, order.isSpecialDay, order.dessertCount, order.mainCount)
        val finalAmount = order.totalAmount - totalDiscount

        outputView.printOrderSummary(order, discount, finalAmount)
        outputView.printBadge(badge)

    } catch (e: IllegalArgumentException) {
        println("[ERROR] ${e.message}")
        // 잘못된 입력이 있을 경우, 해당 부분부터 입력을 다시 받습니다.
        main()
    }
}