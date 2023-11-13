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
    // 상수 선언

    fun applyEvent(order: Order): String {
        validateOrder(order)
        val totalDiscount = calculateTotalDiscount(order)
        return assignBadge(totalDiscount)
    }


}