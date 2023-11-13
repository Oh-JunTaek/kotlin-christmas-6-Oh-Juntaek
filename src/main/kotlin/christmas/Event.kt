package christmas

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
    }//최소 주문 금액, 최대 메뉴 개수, 배지 부여 기준 상수 선언

    fun applyEvent(order: Order): String {
        val totalDiscount = if (order.totalAmount >= MIN_ORDER_AMOUNT) {
            calculateTotalDiscount(order) // 총 할인 금액 계산
        } else {
            0
        }
        return assignBadge(totalDiscount) // 할인 금액에 따른 배지 부여
    }//이벤트 적용 메인 함수

    private fun validateOrder(order: Order) {
        if (order.totalCount > MAX_MENU_COUNT) {
            throw IllegalArgumentException("메뉴는 한 번에 최대 ${MAX_MENU_COUNT}개까지만 주문할 수 있습니다.")
        }
        if (order.beverageCount == order.totalCount) {
            throw IllegalArgumentException("음료만 주문할 수 없습니다.")
        }
    }//주문 검증

    private fun calculateTotalDiscount(order: Order): Int {
        val ddayDiscount = DdayDiscount()
        return ddayDiscount.calculateDiscount(order.date, order.totalAmount, order.isSpecialDay, order.dessertCount, order.mainCount)
    }//할인 금액 계산

    private fun assignBadge(totalDiscount: Int): String {
        var badge = "없음"
        val sortedEntries = BADGE_THRESHOLDS.entries.sortedBy { it.value }
        for ((key, value) in sortedEntries) {
            if (totalDiscount >= value) {
                badge = key
            }
        }
        return badge
    }//할인 금액에 따른 배지 부여
}