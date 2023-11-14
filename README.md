# 12월 이벤트 플래너 개발

## 작업 순서
- 요구사항 읽고 Read.me로 정리하기. 
- 외적인 상황 놓치지 않기(우테코 초대하기/프라이빗)
- 작업 목록 작성하기
- 테스트 진행 및 테스트코드 작성
- 수정 보완(이하 체크리스트)
1. 1함수 1기능 + 함수 길이 15이하
2. 하드코딩최소화
3. else 지양
4. indent depth 3 미만
5. 올바른 오류 코드 작성 (Exception이 아닌 IllegalArgumentException, IllegalStateException)
- 최종 점검
  

## 구현해야 할 목록
- 메인 함수(Application.kt)
1. 프로그램 실행의 시작점은 Application의 main()이다.

- 각종 할인 계산식(DdayDiscount.kt)
1. calculateDiscount : 주문 날짜, 총 주문 금액, 특별 날짜 여부, 디저트 개수, 메인 요리 개수를 인자로 받아 총 할인 금액을 반환.
2. calculateFinalAmount : 총 주문 금액과 총 할인 금액을 인자로 받아 최종 결제 금액을 계산.
3. calculateDdayDiscount : 이벤트 시작일로부터의 날짜를 기준으로 D-day 할인 금액을 계산.
4. calculateWeekdayDiscount : 평일에 디저트를 주문한 경우에 적용되는 할인 금액을 계산.
5. calculateWeekendDiscount : 주말에 메인 요리를 주문한 경우에 적용되는 할인 금액을 계산.
6. calculateSpecialDiscount : 특별 날짜에 주문한 경우에 적용되는 할인 금액을 계산.
7. calculateGiftEvent : 총 주문 금액이 일정 금액 이상인 경우에 적용되는 증정품의 가격을 계산.
8. calculateSpecialDayDiscount : 특정 날짜에 주문한 경우에 적용되는 할인 금액을 계산.
9. validateDate : 주문 날짜가 이벤트 기간 내에 있는지를 검증.

- 입력값 처리(InputView.kt)
1. readDate : 식당 방문 예정일을 입력받음. 입력은 숫자를 통해 이루어지며, 숫자가 1~31 사이가 아니면 에러 메시지를 출력하고 다시 입력을 요청.
2. readOrder : 주문 정보를 입력받습니다. 각 메뉴와 그에 해당하는 개수는 '콤마'로 구분하며, 메뉴 이름과 개수는 -로 연결. 입력받은 주문 정보는 Order 객체로 변환되어 반환.
3. validateOrderItem : 입력 형식, 메뉴 이름, 메뉴 개수, 중복 메뉴 유효성검사.
4. calculateTotalCount : 주문한 메뉴의 총 개수를 계산.
5. validateInputFormat : 주문 항목이 올바른 형식(메뉴-개수)으로 입력되었는지 검사.
6. validateMenu : 메뉴 이름이 유효한지 검사.
7. validateCount : 주문 개수가 유효한지 검사. 주문 개수는 1 이상.
8. validateDuplicate : 동일 메뉴 주문 검사.
9. validateTotalCount : 주문한 메뉴의 총 개수가 20개를 초과하지 않았는지 검사.
10. validateBeverageOnly : 음료만 주문하지 않았는지 검사.

- 출력값 처리(OutputView.kt)
1. printOrderSummary 함수: 이 함수는 주문 요약 정보를 출력.
2. 주문 정보(order), 할인 정보(discount), 최종 결제 금액(finalAmount)을 인자로 받.
3. 먼저 각각의 할인 정보를 계산하고 할인 내역을 저장하기 위한 discountDetails 리스트를 생성.
4. 할인 금액이 존재하는 경우, 이를 할인 내역 리스트에 추가.
5. 주문 날짜와 주문한 메뉴를 출력.
6. 할인 전 총 주문 금액을 출력.
7. 증정 메뉴를 출력합니다. 주문 금액이 120,000원 이상인 경우 샴페인을 증정.
8. 혜택 내역을 출력합니다. 할인 내역 리스트가 비어있지 않은 경우, 각각의 항목을 출력.
9. 총 혜택 금액을 출력.
10. 할인 후 예상 결제 금액을 출력.
11. 12월 이벤트 배지를 출력.

- 메뉴 처리(Menu.kt)
1. MenuCategory 열거형: enum class를 이용. 카테고리는 애피타이저, 메인, 디저트, 음료로 구성.
2. Menu 데이터 클래스: 메뉴의 이름, 가격, 카테고리 정보를 담고 있는 데이터 클래스.
3. MenuList 클래스: 메뉴 목록을 저장. 각 메뉴 카테고리에 해당하는 메뉴들은 APPETIZERS, MAINS, DESSERTS, BEVERAGES, 전체 메뉴 목록은 ALL_MENUS에 저장.

- 주문 처리(Order.kt)
1. Order 데이터 클래스: 주문에 대한 정보를 표현. 주문 날짜, 총 주문 금액, 총 주문 개수, 주문한 음료 개수, 주문한 디저트 개수, 주문한 메인 요리 개수, 특별한 날인지 여부, 주문 항목 리스트를 속성으로 가짐.
2. parseOrder : 문자열 형태의 주문 정보를 Order 객체로 변환합니다. 문자열은 '콤마'로 구분된 주문 항목으로 구성. 각 주문 항목은 메뉴 이름과 개수를 -로 연결한 형태.
3. parseOrderItem : 문자열 형태의 주문 항목을 OrderItem 객체로 변환합니다.
4. validateOrderItemFormat : 주문 항목이 올바른 형식(메뉴-개수)으로 입력되었는지 검사합니다.
5. findMenu : 메뉴 이름이 유효한지 검사하고, 해당 메뉴를 반환합니다.
6. validateQuantity : 주문 개수가 유효한지 검사하고, 해당 개수를 반환합니다.
7. validateUniqueMenu : 동일한 메뉴를 중복해서 주문하지 않았는지 검사합니다.
8. createOrder : 주문 정보를 바탕으로 Order 객체를 생성합니다.
9. calculateTotalAmount : 총 주문 금액 계산
10. calculateTotalCount : 총 주문 개수 계산
11. calculateBeverageCount : 주문한 음료 계산
12. calculateDessertCount : 주문한 디저트 개수 계산
13. calculateMainCount : 주문한 메인 요리 개수를 계산.
14. OrderItem 데이터 클래스: 주문 항목에 대한 정보를 표현. 메뉴와 개수.

- 이벤트 처리(Event.kt)
1. applyEvent : 주문에 이벤트를 적용. 주문 금액이 최소 주문 금액 이상인 경우, 총 할인 금액을 계산하고 이에 따라 배지를 부여.
2. validateOrder : 주문의 유효성을 검사. 주문한 메뉴의 총 개수가 최대 메뉴 개수를 초과하거나, 주문한 모든 메뉴가 음료인 경우 에러를 발생.
3. calculateTotalDiscount : 총 할인 금액을 계산. DdayDiscount 클래스를 사용해 할인 금액과 증정 이벤트에 따른 할인 금액을 계산.
4. assignBadge : 할인 금액에 따라 배지를 부여합니다. 할인 금액이 배지 부여 기준을 만족하면 해당 배지를 부여.


## 주요 기능 사항 체크리스트
- 크리스마스 디데이 할인(1,000원 시작 100원씩 증가/12.1~12.25까지 적용)
- 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
- 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
- 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인(일요일+크리스마스 월요일25일)
- 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
- 이벤트 배지 부여(5,000-별/10,000-트리/20,000-산타)
- 모든 이벤트는 10,000원 이상 주문시
- 음료만 주문 불가능
- 메뉴는 한 번에 최대 20개까지만 주문 가능
- 적절한 [ERROR] 메시지 사용
- 표기 내역 확인하기
