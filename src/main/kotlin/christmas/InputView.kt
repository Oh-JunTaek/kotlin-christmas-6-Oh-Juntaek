package christmas

import camp.nextstep.edu.missionutils.Console
import java.time.LocalDate
import java.util.Locale

class InputView {
    fun readDate(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val input = Console.readLine()
        // 입력값을 숫자로 변환하고, 변환할 수 없는 경우 예외를 발생시킵니다.
        return input.toIntOrNull() ?: throw IllegalArgumentException("날짜는 숫자만 입력해 주세요.")
    }

    fun readOrder(): Order {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val input = Console.readLine()
        // TODO: 입력값을 파싱하여 Order 객체를 생성하는 로직 필요
        return parseOrder(input)
    }

    private fun parseOrder(input: String): Order {
        val orderItems = input.split(",").map { it.trim() } // 각 메뉴와 개수를 구분합니다.
        val orders = orderItems.map { parseOrderItem(it) } // 각 메뉴와 개수를 OrderItem 객체로 변환합니다.
        val totalAmount = orders.sumBy { it.menu.price * it.quantity }
        val totalCount = orders.sumBy { it.quantity }
        val beverageCount = orders.count { it.menu.category == MenuCategory.BEVERAGE }
        val dessertCount = orders.count { it.menu.category == MenuCategory.DESSERT }
        val mainCount = orders.count { it.menu.category == MenuCategory.MAIN }
        val isSpecialDay = false

        return Order(LocalDate.now(), totalAmount, totalCount, beverageCount, dessertCount, mainCount, isSpecialDay, orders)
    }

    private fun parseOrderItem(orderItem: String): OrderItem {
        val (menuName, quantityStr) = orderItem.split("-") // 메뉴 이름과 개수를 구분합니다.
        val menu = MenuList.ALL_MENUS[menuName.trim()] // 메뉴 리스트에서 해당 메뉴를 찾습니다.
            ?: throw IllegalArgumentException("메뉴 이름이 올바르지 않습니다.")
        val quantity = quantityStr.trim().toIntOrNull() ?: throw IllegalArgumentException("개수는 숫자만 입력해 주세요.")
        return OrderItem(menu, quantity)
    }
}