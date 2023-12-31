package christmas

import java.text.NumberFormat

class OutputView {
    fun printOrderSummary(order: Order, discount: DdayDiscount, finalAmount: Int) {
        val discountDetails = mutableListOf<String>()
        val formatter = NumberFormat.getNumberInstance()
        var ddayDiscount = 0
        var weekdayDiscount = 0
        var weekendDiscount = 0
        var specialDayDiscount = 0
        var giftDiscount = 0

        if (order.totalAmount >= Event.MIN_ORDER_AMOUNT) {
            ddayDiscount = discount.calculateDdayDiscount(order.date.dayOfMonth)
            weekdayDiscount = discount.calculateWeekdayDiscount(order.date.dayOfMonth, order.dessertCount)
            weekendDiscount = discount.calculateWeekendDiscount(order.date.dayOfMonth, order.mainCount)
            specialDayDiscount = discount.calculateSpecialDayDiscount(order.date.dayOfMonth)
            giftDiscount = discount.calculateGiftEvent(order.totalAmount)

            if (ddayDiscount > 0) {
                discountDetails.add("크리스마스 디데이 할인: -${formatter.format(ddayDiscount.toLong())}원") }
            if (weekdayDiscount > 0) {
                discountDetails.add("평일 할인: -${formatter.format(weekdayDiscount.toLong())}원") }
            if (weekendDiscount > 0) {
                discountDetails.add("주말 할인: -${formatter.format(weekendDiscount.toLong())}원") }
            if (specialDayDiscount > 0) {
                discountDetails.add("특별 할인: -${formatter.format(DdayDiscount.SPECIAL_DISCOUNT.toLong())}원") }
            if (giftDiscount > 0) {
                discountDetails.add("증정 이벤트: -${formatter.format(giftDiscount.toLong())}원") }
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
        val totalDiscount = ddayDiscount + weekdayDiscount + weekendDiscount + specialDayDiscount + giftDiscount
        println("-${formatter.format(totalDiscount.toLong())}원")
        println("\n<할인 후 예상 결제 금액>")
        println("${formatter.format((order.totalAmount - totalDiscount + giftDiscount).toLong())}원")


        println("\n<12월 이벤트 배지>")

        val event = Event()
        println(event.applyEvent(order))
    }
}