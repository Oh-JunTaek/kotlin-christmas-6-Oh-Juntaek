package christmas

import org.junit.jupiter.api.Order
import java.time.LocalDate

class Event {
    companion object {
        const val MIN_ORDER_AMOUNT = 10000
        const val MAX_MENU_COUNT = 20
        val BADGE_THRESHOLDS = mapOf(
            "별" to 5000,
            "트리" to 10000,
            "산타" to 20000
        )
    }
    // 상수 선언

    fun applyEvent(order: Order): String {
        validateOrder(order)
        val totalDiscount = calculateTotalDiscount(order)
        return assignBadge(totalDiscount)
    }


    private fun validateOrder(order: Order) {
        if (order.totalAmount < MIN_ORDER_AMOUNT) {
            throw IllegalArgumentException("총주문 금액은 ${MIN_ORDER_AMOUNT}원 이상이어야 합니다.")
        }
        if (order.totalCount > MAX_MENU_COUNT) {
            throw IllegalArgumentException("메뉴는 한 번에 최대 ${MAX_MENU_COUNT}개까지만 주문할 수 있습니다.")
        }
        if (order.beverageCount == order.totalCount) {
            throw IllegalArgumentException("음료만 주문할 수 없습니다.")
        }
    }
    //주문 예외상황설정

    private fun calculateTotalDiscount(order: Order): Int {
        //할인 계산식 추가 예정
        return 0
    }
    private fun assignBadge(totalDiscount: Int): String {
        var badge = ""
        for ((key, value) in BADGE_THRESHOLDS) {
            if (totalDiscount >= value) {
                badge = key
            }
        }
        return badge
    }
}

data class Order(
    val date: LocalDate,
    val totalAmount: Int,
    val totalCount: Int,
    val beverageCount: Int,
    val dessertCount: Int,
    val mainCount: Int,
    val isSpecialDay: Boolean
)