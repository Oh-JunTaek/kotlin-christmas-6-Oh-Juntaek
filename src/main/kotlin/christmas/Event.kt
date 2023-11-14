package christmas

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
    fun applyEvent(order: Order): String {
        val totalDiscount = if (order.totalAmount >= MIN_ORDER_AMOUNT) {
            calculateTotalDiscount(order) // 총 할인 금액 계산
        } else {
            0
        }
        return assignBadge(totalDiscount) // 할인 금액에 따른 배지 부여
    }
    private fun calculateTotalDiscount(order: Order): Int {
        val ddayDiscount = DdayDiscount()
        var totalDiscount = ddayDiscount.calculateDiscount(order.date, order.totalAmount, order.isSpecialDay, order.dessertCount, order.mainCount)
        totalDiscount += ddayDiscount.calculateGiftEvent(order.totalAmount)
        return totalDiscount
    }
    private fun assignBadge(totalDiscount: Int): String {
        var badge = "없음"
        val sortedEntries = BADGE_THRESHOLDS.entries.sortedBy { it.value }
        for ((key, value) in sortedEntries) {
            if (totalDiscount >= value) {
                badge = key
            }
        }
        return badge
    }
}