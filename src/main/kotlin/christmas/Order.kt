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
    companion object {
         fun parseOrder(input: String, date: LocalDate): Order {
             val orderItems = input.split(",").map { it.trim() } // 각 메뉴와 개수를 구분합니다.
             val orders = orderItems.map { parseOrderItem(it) } // 각 메뉴와 개수를 OrderItem 객체로 변환합니다.
             val uniqueMenuCount = orders.map { it.menu.name }.distinct().count()
             if (uniqueMenuCount != orders.size) {
                 throw IllegalArgumentException("중복된 메뉴가 있습니다.")
             }
             val totalAmount = orders.sumBy { it.menu.price * it.quantity }
             val totalCount = orders.sumBy { it.quantity }
             val beverageCount = orders.count { it.menu.category == MenuCategory.BEVERAGE }
             val dessertCount = orders.count { it.menu.category == MenuCategory.DESSERT }
             val mainCount = orders.count { it.menu.category == MenuCategory.MAIN }
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

             private fun parseOrderItem(orderItem: String): OrderItem {
                 if (!orderItem.contains("-")) {
                     throw IllegalArgumentException("[ERROR] 메뉴와 개수는 '-'로 구분하여 입력해 주세요.")
                 }
                 val (menuName, quantityStr) = orderItem.split("-") // 메뉴 이름과 개수를 구분합니다.
                 val menu = MenuList.ALL_MENUS[menuName.trim()] // 메뉴 리스트에서 해당 메뉴를 찾습니다.
                     ?: throw IllegalArgumentException("메뉴 이름이 올바르지 않습니다.")
                 val quantity = quantityStr.trim().toIntOrNull()
                 if (quantity == null || quantity < 1) {
                     throw IllegalArgumentException("개수는 1 이상의 숫자만 입력해 주세요.")
                 }
                 return OrderItem(menu, quantity)
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