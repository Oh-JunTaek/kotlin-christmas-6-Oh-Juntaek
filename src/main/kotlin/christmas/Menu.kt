package christmas

data class Menu(val name: String, val price: Int)

class MenuList {
    companion object {
        val APPETIZERS = mapOf(
            "양송이수프" to Menu("양송이수프", 6000),
            "타파스" to Menu("타파스", 5500),
            "시저샐러드" to Menu("시저샐러드", 8000)
        )

        val MAINS = mapOf(
            "티본스테이크" to Menu("티본스테이크", 55000),
            "바비큐립" to Menu("바비큐립", 54000),
            "해산물파스타" to Menu("해산물파스타", 35000),
            "크리스마스파스타" to Menu("크리스마스파스타", 25000)
        )

        val DESSERTS = mapOf(
            "초코케이크" to Menu("초코케이크", 15000),
            "아이스크림" to Menu("아이스크림", 5000)
        )

        val BEVERAGES = mapOf(
            "제로콜라" to Menu("제로콜라", 3000),
            "레드와인" to Menu("레드와인", 60000),
            "샴페인" to Menu("샴페인", 25000)
        )

        val ALL_MENUS = APPETIZERS + MAINS + DESSERTS + BEVERAGES
    }
}