package christmas

import camp.nextstep.edu.missionutils.Console
import christmas.Order.Companion.parseOrder
import java.time.LocalDate

class InputView {
    companion object {
        private const val INVALID_DATE_ERROR = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
        private const val NON_NUMERIC_DATE_ERROR = "[ERROR] 날짜는 숫자만 입력해 주세요."
        private const val ORDER_QUESTION = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
        private const val INVALID_ORDER_ERROR = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
        private const val MENUMAXIMAM = "[ERROR] 메뉴는 20개 까지만 주문 가능합니다. 다시 입력해주세요."
        private const val BEVERAGEONLY = "[ERRROR] 음료만 주문할 수 없습니다. 다시 입력해주세요. "
        private val DATE_RANGE = 1..31
        private val YEAR = 2023
        private val MONTH = 12

    }//상수 선언
    fun readDate(): Int {
        while (true) {
            try {
                println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
                val input = Console.readLine()
                val day = input!!.toInt()

                if (day !in DATE_RANGE) {
                    println(INVALID_DATE_ERROR)
                    continue
                }

                return day

            } catch (e: IllegalArgumentException) {
                println(INVALID_DATE_ERROR)
            }
        }
    }
 //방문 날짜 입력 받기 및 예외 확인
    fun readOrder(date: LocalDate): Order {
        println(ORDER_QUESTION)
        while (true) {
            try {
                val input = Console.readLine()!!
                val orderItems = input.split(",")
                var totalCount = 0
                val orderedMenus = mutableSetOf<String>()
                var isBeverageOnly = true
                for (item in orderItems) {
                    val (menu, count) = validateOrderItem(item, orderedMenus)
                    totalCount = calculateTotalCount(totalCount, count)
                    if (menu !in MenuList.BEVERAGES) {
                        isBeverageOnly = false
                    }
                }
                validateTotalCount(totalCount)
                validateBeverageOnly(isBeverageOnly)
                val order = parseOrder(input, date)
                return order
            } catch (e: IllegalArgumentException) {
                println(INVALID_ORDER_ERROR)
            }
        }
    }//주문 정보 받기


    fun validateOrderItem(item: String, orderedMenus: MutableSet<String>): Pair<String, Int> {
        val splitItem = item.split("-")
        validateInputFormat(splitItem)
        val menu = splitItem[0]
        val count = splitItem[1].toInt()
        validateMenu(menu)
        validateCount(count)
        validateDuplicate(orderedMenus, menu)
        return Pair(menu, count)
    }

    fun calculateTotalCount(totalCount: Int, count: Int): Int {
        return totalCount + count
    }//총 주문 개수 계산

    fun validateInputFormat(splitItem: List<String>) {
        if (splitItem.size != 2) {
            throw IllegalArgumentException(INVALID_ORDER_ERROR)
        }
    }//올바른 형식인지 확인 '-'를 포함

    fun validateMenu(menu: String) {
        if (menu !in MenuList.ALL_MENUS.keys) {
            throw IllegalArgumentException(INVALID_ORDER_ERROR)
        }
    }//메뉴 이름의 유효성 검사

    fun validateCount(count: Int) {
        if (count < 1) {
            throw IllegalArgumentException(INVALID_ORDER_ERROR)
        }
    }//입력받은 개수 유효성 검사

    fun validateDuplicate(orderedMenus: MutableSet<String>, menu: String) {
        if (!orderedMenus.add(menu)) {
            throw IllegalArgumentException(INVALID_ORDER_ERROR)
        }
    }//중복 메뉴 검사

    fun validateTotalCount(totalCount: Int) {
        if (totalCount > 20) {
            throw IllegalArgumentException(MENUMAXIMAM)
        }
    }//메뉴 총합이 20을 초과하는지 확인

    fun validateBeverageOnly(isBeverageOnly: Boolean) {
        if (isBeverageOnly) {
            throw IllegalArgumentException(BEVERAGEONLY)
        }
    }//음료만 주문하였는지 확인
}