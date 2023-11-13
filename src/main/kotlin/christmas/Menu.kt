package christmas

data class Menu(val name: String, val price: Int)

class MenuList {
    companion object {
        // 메뉴의 이름과 가격 정보를 상수로 선언합니다.
        val APPETIZERS = listOf(
            Menu("양송이수프", 6000),
            Menu("타파스", 5500),
            Menu("시저샐러드", 8000)
        )

        val MAINS = listOf(
            Menu("티본스테이크", 55000),
            Menu("바비큐립", 54000),
            Menu("해산물파스타", 35000),
            Menu("크리스마스파스타", 25000)
        )

        val DESSERTS = listOf(
            Menu("초코케이크", 15000),
            Menu("아이스크림", 5000)
        )

        val BEVERAGES = listOf(
            Menu("제로콜라", 3000),
            Menu("레드와인", 60000),
            Menu("샴페인", 25000)
        )
    }

    private val appetizers = APPETIZERS.toMutableList()
    private val mains = MAINS.toMutableList()
    private val desserts = DESSERTS.toMutableList()
    private val beverages = BEVERAGES.toMutableList()
}