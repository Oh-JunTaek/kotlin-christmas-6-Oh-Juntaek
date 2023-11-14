package christmas

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class SelfTest {
    private val inputView = InputView()

    @Test
    fun `음료만 주문하였을 때 예외를 던져야 한다`() {
        assertThrows<IllegalArgumentException> {
            inputView.validateBeverageOnly(true)
        }
    }

    @Test
    fun `음료만 주문하지 않았을 때 예외를 던지지 않아야 한다`() {
        assertDoesNotThrow {
            inputView.validateBeverageOnly(false)
        }
    }
    @Test
    fun `중복 메뉴 검사에서 중복 메뉴가 있을 경우 예외가 발생해야 한다`() {
        val orderedMenus = mutableSetOf("티본스테이크")
        assertThrows<IllegalArgumentException> {
            inputView.validateDuplicate(orderedMenus, "티본스테이크")
        }
    }

    @Test
    fun `중복 메뉴 검사에서 중복 메뉴가 없을 경우 예외가 발생하지 않아야 한다`() {
        val orderedMenus = mutableSetOf("티본스테이크")
        assertDoesNotThrow {
            inputView.validateDuplicate(orderedMenus, "파스타")
        }
    }

    @Test
    fun `메뉴 총합이 20을 초과할 경우 예외가 발생해야 한다`() {
        assertThrows<IllegalArgumentException> {
            inputView.validateTotalCount(21)
        }
    }

    @Test
    fun `메뉴 총합이 20이하일 경우 예외가 발생하지 않아야 한다`() {
        assertDoesNotThrow {
            inputView.validateTotalCount(20)
        }
    }
    @Test
    fun `올바른 형식 확인에서 형식이 틀렸을 경우 예외가 발생해야 한다`() {
        val splitItem = listOf("티본스테이크")
        assertThrows<IllegalArgumentException> {
            inputView.validateInputFormat(splitItem)
        }
        val splitItem2 = listOf("티본스테이크", "2", "추가")
        assertThrows<IllegalArgumentException> {
            inputView.validateInputFormat(splitItem2)
        }
    }

    @Test
    fun `올바른 형식 확인에서 형식이 맞을 경우 예외가 발생하지 않아야 한다`() {
        val splitItem = listOf("티본스테이크", "2")
        assertDoesNotThrow {
            inputView.validateInputFormat(splitItem)
        }
    }

    @Test
    fun `메뉴 이름 유효성 검사에서 메뉴 이름이 잘못되었을 경우 예외가 발생해야 한다`() {
        assertThrows<IllegalArgumentException> {
            inputView.validateMenu("잘못된메뉴")
        }
    }

    @Test
    fun `메뉴 이름 유효성 검사에서 메뉴 이름이 정확할 경우 예외가 발생하지 않아야 한다`() {
        assertDoesNotThrow {
            inputView.validateMenu("티본스테이크")
        }
    }

    @Test
    fun `입력받은 개수 유효성 검사에서 개수가 1 미만일 경우 예외가 발생해야 한다`() {
        assertThrows<IllegalArgumentException> {
            inputView.validateCount(0)
        }
    }

    @Test
    fun `입력받은 개수 유효성 검사에서 개수가 1 이상일 경우 예외가 발생하지 않아야 한다`() {
        assertDoesNotThrow {
            inputView.validateCount(1)
        }
    }
    @Test
    fun `주문 항목 유효성 검사에서 주문 항목이 유효할 경우 예외가 발생하지 않아야 한다`() {
        val orderedMenus = mutableSetOf<String>()
        assertDoesNotThrow {
            inputView.validateOrderItem("티본스테이크-1", orderedMenus)
        }
    }

    @Test
    fun `주문 항목 유효성 검사에서 주문 항목이 유효하지 않을 경우 예외가 발생해야 한다`() {
        val orderedMenus = mutableSetOf<String>()
        assertThrows<IllegalArgumentException> {
            inputView.validateOrderItem("잘못된메뉴-1", orderedMenus)
        }
    }

    @Test
    fun `총 주문 개수 계산에서 주문 개수가 정확하게 계산되어야 한다`() {
        val totalCount = inputView.calculateTotalCount(10, 5)
        assertEquals(totalCount, 15)
    }

    @Test
    fun `주문 입력이 유효하면 주문이 정상적으로 처리되어야 한다`() {
        val date = LocalDate.now()
        assertDoesNotThrow {
            val input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"
            val order = Order.parseOrder(input, date)
            assertEquals(order.items.size, 4)
            assertEquals(order.totalCount, 5)
        }
    }
    @Test
    fun `validateOrderItem에서 잘못된 메뉴 이름이 주어졌을 때 예외를 던져야 한다`() {
        val orderedMenus = mutableSetOf<String>()
        assertThrows<IllegalArgumentException> {
            inputView.validateOrderItem("잘못된메뉴-1", orderedMenus)
        }
    }

    @Test
    fun `validateInputFormat에서 잘못된 형식이 주어졌을 때 예외를 던져야 한다`() {
        val splitItem = listOf("티본스테이크")
        assertThrows<IllegalArgumentException> {
            inputView.validateInputFormat(splitItem)
        }
    }

    @Test
    fun `validateMenu에서 잘못된 메뉴 이름이 주어졌을 때 예외를 던져야 한다`() {
        assertThrows<IllegalArgumentException> {
            inputView.validateMenu("잘못된메뉴")
        }
    }

    @Test
    fun `validateCount에서 0 또는 음수가 주어졌을 때 예외를 던져야 한다`() {
        assertThrows<IllegalArgumentException> {
            inputView.validateCount(0)
        }
        assertThrows<IllegalArgumentException> {
            inputView.validateCount(-1)
        }
    }
}