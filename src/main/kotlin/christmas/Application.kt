package christmas

import camp.nextstep.edu.missionutils.Console
fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val event = Event()

    try {
        val date = inputView.readDate()
        val order = inputView.readOrder()

        val badge = event.applyEvent(order)

        outputView.printMenu(order)
        // TODO: 출력 로직을 구현
        outputView.printBadge(badge)

    } catch (e: IllegalArgumentException) {
        println("[ERROR] ${e.message}")
        // 잘못된 입력이 있을 경우, 해당 부분부터 입력을 다시 받습니다.
        main()
    }
}
