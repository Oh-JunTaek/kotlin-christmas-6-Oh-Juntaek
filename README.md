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
- 기능별 대 분류
1. 메인 함수(프로그램 실행의 시작점은 Application의 main()이다.)
2. 각종 할인 계산식(DdayDiscount.KT)
3. 입력값 처리(InputView.KT)
4. 출력값 처리(OutputView.KT)
5. 메뉴 처리()
6. 주문 처리()
7. 이벤트 처리(Event.KT)
- 

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

## 나만의 체크리스트
1. 라이브러리
camp.nextstep.edu.missionutils에서 제공하는 Console API를 사용하여 구현해야 한다.
사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다.
2.InputView, OutputView 클래스를 참고하여 입출력 클래스를 구현한다.
입력과 출력을 담당하는 클래스를 별도로 구현한다.
해당 클래스의 패키지, 클래스명, 메서드의 반환 타입과 시그니처는 자유롭게 구현할 수 있다.
3.사용자가 잘못된 값을 입력할 경우 IllegalArgumentException를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
Exception이 아닌 IllegalArgumentException, IllegalStateException 등과 같은 명확한 유형을 처리한다.
4.도메인 로직에 단위 테스트를 구현해야 한다. 단, UI(System.out, System.in, Scanner) 로직은 제외한다.
핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
5.else를 지양한다.
힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
때로는 if/else, when문을 사용하는 것이 더 깔끔해 보일 수 있다. 어느 경우에 쓰는 것이 적절할지 스스로 고민해 본다.
6.함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
7.indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
8.프로그램 실행의 시작점은 Application의 main()이다.
build.gradle(.kts)을 변경할 수 없고, 외부 라이브러리를 사용하지 않는다.
