package christmas

import java.time.LocalDate

data class Order(
    val date: LocalDate,
    val totalAmount: Int,
    val totalCount: Int,
    val beverageCount: Int,
    val dessertCount: Int,
    val mainCount: Int,
    val isSpecialDay: Boolean,
    val items: List<OrderItem>
){
    val dayOfMonth: Int
        get() = date.dayOfMonth
    companion object {
        fun parseOrder(input: String, date: LocalDate): Order {
            val orderItems = input.split(",").map { it.trim() }
            val orders = orderItems.map { parseOrderItem(it) }
            validateUniqueMenu(orders)
            return createOrder(date, orders)
        }

        private fun parseOrderItem(orderItem: String): OrderItem {
            validateOrderItemFormat(orderItem)
            val (menuName, quantityStr) = orderItem.split("-")
            val menu = findMenu(menuName.trim())
            val quantity = validateQuantity(quantityStr.trim())
            return OrderItem(menu, quantity)
        }

        private fun validateOrderItemFormat(orderItem: String) {
            if (!orderItem.contains("-")) {
                throw IllegalArgumentException("[ERROR] 메뉴와 개수는 '-'로 구분하여 입력해 주세요.")
            }
        }

        private fun findMenu(menuName: String): Menu {
            return MenuList.ALL_MENUS[menuName]
                ?: throw IllegalArgumentException("메뉴 이름이 올바르지 않습니다.")
        }

        private fun validateQuantity(quantityStr: String): Int {
            val quantity = quantityStr.toIntOrNull()
            if (quantity == null || quantity < 1) {
                throw IllegalArgumentException("개수는 1 이상의 숫자만 입력해 주세요.")
            }
            return quantity
        }

        private fun validateUniqueMenu(orders: List<OrderItem>) {
            val uniqueMenuCount = orders.map { it.menu.name }.distinct().count()
            if (uniqueMenuCount != orders.size) {
                throw IllegalArgumentException("중복된 메뉴가 있습니다.")
            }
        }

        private fun createOrder(date: LocalDate, orders: List<OrderItem>): Order {
            val totalAmount = orders.sumBy { it.menu.price * it.quantity }
            val totalCount = orders.sumBy { it.quantity }
            val beverageCount = orders.count { it.menu.category == MenuCategory.BEVERAGE }
            val dessertCount = orders.filter { it.menu.category == MenuCategory.DESSERT }.sumBy { it.quantity }
            val mainCount = orders.filter { it.menu.category == MenuCategory.MAIN }.sumBy { it.quantity }
            val isSpecialDay = false
            return Order(
                date,
                totalAmount,
                totalCount,
                beverageCount,
                dessertCount,
                mainCount,
                isSpecialDay,
                orders
            )
        }
    }

    fun printOrder() {
        println("12월 ${date.dayOfMonth}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")
        println("<주문 메뉴>")
        items.forEach { item ->
            println("${item.menu.name} ${item.quantity}개")
        }
    }
}

data class OrderItem(val menu: Menu, val quantity: Int)