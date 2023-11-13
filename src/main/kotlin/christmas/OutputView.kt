package christmas

class OutputView {
    fun printOrderSummary(order: Order, discount: DdayDiscount, finalAmount: Int) {
        println("<주문 메뉴>")
        order.items.forEach {
            println("${it.menu.name} ${it.quantity}개")
        }
        println("\n<할인 전 총주문 금액>")
        println("${order.totalAmount}원")

        println("\n<혜택 내역>")
        println("크리스마스 디데이 할인: -${discount.calculateDdayDiscount(order.date)}원")
        println("평일 할인: -${discount.calculateWeekdayDiscount(order.date, order.dessertCount)}원")
        println("주말 할인: -${discount.calculateWeekendDiscount(order.date, order.mainCount)}원")
        println("특별 할인: -${discount.calculateSpecialDiscount(order.isSpecialDay)}원")
        println("증정 이벤트: -${discount.calculateGiftEvent(order.totalAmount)}원")

        println("\n<총혜택 금액>")
        println("-${discount.calculateDiscount(order.date, order.totalAmount, order.isSpecialDay, order.dessertCount, order.mainCount)}원")

        println("\n<할인 후 예상 결제 금액>")
        println("${finalAmount}원")

        println("\n<12월 이벤트 배지>")
        val event = Event()
        println(event.applyEvent(order))
    }
}