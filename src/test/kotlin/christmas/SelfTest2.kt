package christmas

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.LocalDate

class SelfTest2 {
    private val ddayDiscount = DdayDiscount()
    private val event = Event()

    @Test
    fun `Dday 할인 계산 테스트`() {
        val discount = ddayDiscount.calculateDdayDiscount(10)
        assertThat(discount).isEqualTo(1900)
    }

    @Test
    fun `평일 할인 계산 테스트`() {
        val discount = ddayDiscount.calculateWeekdayDiscount(5, 2)
        assertThat(discount).isEqualTo(4046)
    }

    @Test
    fun `주말 할인 계산 테스트`() {
        val discount = ddayDiscount.calculateWeekendDiscount(1, 2)
        assertThat(discount).isEqualTo(4046)
    }

    @Test
    fun `특별 할인 계산 테스트`() {
        val discount = ddayDiscount.calculateSpecialDiscount(true)
        assertThat(discount).isEqualTo(1000)
    }

    @Test
    fun `특별 일 할인 계산 테스트`() {
        val discount = ddayDiscount.calculateSpecialDayDiscount(3)
        assertThat(discount).isEqualTo(1000)
    }

    @Test
    fun `할인 계산 테스트`() {
        val date = LocalDate.of(2023, 12, 10)
        val totalAmount = 200000
        val isSpecialDay = true
        val dessertCount = 2
        val mainCount = 2
        val discount = ddayDiscount.calculateDiscount(date, totalAmount, isSpecialDay, dessertCount, mainCount)

        val expectedDiscount = ddayDiscount.calculateDdayDiscount(date.dayOfMonth) +
                ddayDiscount.calculateWeekdayDiscount(date.dayOfMonth, dessertCount) +
                ddayDiscount.calculateWeekendDiscount(date.dayOfMonth, mainCount) +
                ddayDiscount.calculateSpecialDiscount(isSpecialDay)

        assertThat(discount).isEqualTo(expectedDiscount)
    }
    @Test
    fun `적용 이벤트 테스트`() {
        val orderItems = listOf(
            Order.OrderItem(Menu("티본스테이크", 55000, MenuCategory.MAIN), 1),
            Order.OrderItem(Menu("초코케이크", 15000, MenuCategory.DESSERT), 1)
        )
        val order = Order(
            date = LocalDate.of(2023, 12, 10),
            totalAmount = 70000,
            totalCount = 2,
            beverageCount = 0,
            dessertCount = 1,
            mainCount = 1,
            isSpecialDay = true,
            items = orderItems
        )
        val badge = event.applyEvent(order)
        assertThat(badge).isEqualTo("없음")
    }

    @Test
    fun `적용 이벤트 테스트 - 할인 없음`() {
        val orderItems = listOf(
            Order.OrderItem(Menu("티본스테이크", 55000, MenuCategory.MAIN), 1)
        )
        val order = Order(
            date = LocalDate.of(2023, 12, 10),
            totalAmount = 55000,
            totalCount = 1,
            beverageCount = 0,
            dessertCount = 0,
            mainCount = 1,
            isSpecialDay = true,
            items = orderItems
        )
        val badge = event.applyEvent(order)
        assertThat(badge).isEqualTo("없음")
    }

    @Test
    fun `적용 이벤트 테스트 - 최대 할인`() {
        val orderItems = listOf(
            Order.OrderItem(Menu("티본스테이크", 55000, MenuCategory.MAIN), 2),
            Order.OrderItem(Menu("초코케이크", 15000, MenuCategory.DESSERT), 2)
        )
        val order = Order(
            date = LocalDate.of(2023, 12, 25),
            totalAmount = 140000,
            totalCount = 4,
            beverageCount = 0,
            dessertCount = 2,
            mainCount = 2,
            isSpecialDay = true,
            items = orderItems
        )
        val badge = event.applyEvent(order)
        assertThat(badge).isEqualTo("산타")
    }

    @Test
    fun `parseOrder 메서드 테스트`() {
        val order = Order.parseOrder("티본스테이크 - 2, 초코케이크 - 1", LocalDate.of(2023, 12, 25))

        assertNotNull(order)
        assertEquals(LocalDate.of(2023, 12, 25), order.date)
        assertEquals(3, order.totalCount)
        assertEquals(125000, order.totalAmount)
        assertEquals(2, order.mainCount)
        assertEquals(1, order.dessertCount)
        assertEquals(0, order.beverageCount)
        assertEquals(false, order.isSpecialDay)
    }
}