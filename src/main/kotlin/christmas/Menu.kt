package christmas

data class Menu(val name: String, val price: Int)

class MenuList {
    private val appetizers = mutableListOf<Menu>()
    private val mains = mutableListOf<Menu>()
    private val desserts = mutableListOf<Menu>()
    private val beverages = mutableListOf<Menu>()

    init {
        appetizers.add(Menu("양송이수프", 6000))
        appetizers.add(Menu("타파스", 5500))
        appetizers.add(Menu("시저샐러드", 8000))

        mains.add(Menu("티본스테이크", 55000))
        mains.add(Menu("바비큐립", 54000))
        mains.add(Menu("해산물파스타", 35000))
        mains.add(Menu("크리스마스파스타", 25000))

        desserts.add(Menu("초코케이크", 15000))
        desserts.add(Menu("아이스크림", 5000))

        beverages.add(Menu("제로콜라", 3000))
        beverages.add(Menu("레드와인", 60000))
        beverages.add(Menu("샴페인", 25000))
    }
}