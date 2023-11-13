package christmas

import camp.nextstep.edu.missionutils.Console
import christmas.Order.Companion.parseOrder
import java.time.LocalDate

class InputView {
    fun readDate(): LocalDate {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        while (true) {
            try {
                return LocalDate.of(2023, 12, Console.readLine()!!.toInt())
            } catch (e: Exception) {
                println("[ERROR] 날짜는 숫자만 입력해 주세요.")
            }
        }
    }

    fun readOrder(date: LocalDate): Order {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        while (true) {
            try {
                val input = Console.readLine()!!
                val order = parseOrder(input, date)
                return order
            } catch (e: IllegalArgumentException) {
                println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            }
        }
    }
}
