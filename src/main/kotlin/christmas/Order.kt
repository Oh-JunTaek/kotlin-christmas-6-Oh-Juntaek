package christmas

import java.time.LocalDate

data class Order(
    val date: LocalDate,
    val totalAmount: Int,
    val totalCount: Int,
    val beverageCount: Int,
    val dessertCount: Int,
    val mainCount: Int,
    val isSpecialDay: Boolean
)