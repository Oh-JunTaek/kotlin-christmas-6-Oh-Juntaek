package christmas

import java.text.NumberFormat

class OutputView {
    fun printOrderSummary(order: Order, discount: DdayDiscount, finalAmount: Int) {
        val discountDetails = mutableListOf<String>()
        val ddayDiscount = discount.calculateDdayDiscount(order.date)
        val weekdayDiscount = discount.calculateWeekdayDiscount(order.date, order.dessertCount)
        val weekendDiscount = discount.calculateWeekendDiscount(order.date, order.mainCount)
        val specialDiscount = discount.calculateSpecialDiscount(order.isSpecialDay)
        val giftDiscount = discount.calculateGiftEvent(order.totalAmount)
        val formatter = NumberFormat.getNumberInstance()

        if (ddayDiscount > 0) {
            discountDetails.add("크리스마스 디데이 할인: -${ddayDiscount}원")
        }
        if (weekdayDiscount > 0) {
            discountDetails.add("평일 할인: -${weekdayDiscount}원")
        }
        if (weekendDiscount > 0) {
            discountDetails.add("주말 할인: -${weekendDiscount}원")
        }
        if (specialDiscount > 0) {
            discountDetails.add("특별 할인: -${specialDiscount}원")
        }
        if (giftDiscount > 0) {
            discountDetails.add("증정 이벤트: -${giftDiscount}원")
        }

        println("12월 ${order.date.dayOfMonth}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
        println("<주문 메뉴>")
        order.items.forEach {
            println("${it.menu.name} ${it.quantity}개")
        }
        println("\n<할인 전 총주문 금액>")
        println("${formatter.format(order.totalAmount)}원")

        println("\n<증정 메뉴>")
        if (order.totalAmount >= 120000) {
            println("샴페인 1개")
        } else {
            println("없음")
        }

        println("\n<혜택 내역>")
        if (discountDetails.isEmpty()) {
            println("없음")
        } else {
            discountDetails.forEach { println(it) }
        }

        println("\n<총혜택 금액>")
        println("-${formatter.format(discount.calculateDiscount(order.date, order.totalAmount, order.isSpecialDay, order.dessertCount, order.mainCount))}원")

        println("<할인 후 예상 결제 금액>")
        println("${formatter.format(finalAmount)}원")

        println("\n<12월 이벤트 배지>")
        val event = Event()
        println(event.applyEvent(order))
    }
}