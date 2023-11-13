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
    fun printOrder() {
        println("12월 ${date.dayOfMonth}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")
        println("<주문 메뉴>")
        items.forEach { item ->
            println("${item.menu.name} ${item.quantity}개")
        }
    }
}

data class OrderItem(val menu: Menu, val quantity: Int)