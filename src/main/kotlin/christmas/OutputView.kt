package christmas

class OutputView {
    fun printOrderSummary(order: Order, discount: Int, finalAmount: Int) {
        println("<주문 메뉴>")
        order.orders.forEach {
            println("${it.menu.name} ${it.quantity}개")
        }
        println("\n<할인 전 총주문 금액>")
        println("${order.totalAmount}원")

        println("\n<혜택 내역>")
        // 혜택 내역 계산 로직이 필요합니다.

        println("\n<총혜택 금액>")
        println("-${discount}원")

        println("\n<할인 후 예상 결제 금액>")
        println("${finalAmount}원")

        println("\n<12월 이벤트 배지>")
        // 12월 이벤트 배지 출력 로직이 필요합니다.
    }
}