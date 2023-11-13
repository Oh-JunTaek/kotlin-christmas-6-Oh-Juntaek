package christmas

import camp.nextstep.edu.missionutils.Console

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
        // TODO: 입력값을 파싱하여 Order 객체를 생성하는 로직 필요
    }
}