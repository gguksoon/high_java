package basic;
/*
	람다식 ==> 익명함수를 생성하기 위한 식
		 ==> 자바에서는 '매개변수를 가진 코드블럭'으로 '런타임시' '익명구현객체'로 생성된다.
	
	형식)
		인터페이스명 변수명 = 람다식
		
	람다식 형식)
		(자료형이름 변수명1, ...) -> { 실행문들; ... }
		
	규칙)
	1. '자료형이름'을 생략할 수 있다.
		(int a) -> { System.out.println(a); }
		(a) -> { System.out.println(a); }
		
	2. 매개변수가 1개일 경우에는 괄호 '( )'를 생략할 수 있다. 
		a -> { System.out.println(a); }
		
	3. 실행문이 1개이면 중괄호 '{ }'를 생략할 수 있다.	
		a -> System.out.println(a)
		
	4. 매개변수가 없거나 2개 이상일 경우에는 괄호 '( )'를 생략할 수 없다.
		( ) -> { System.out.println("안녕"); }
		
	5. 반환값이 있으면 return 명령을 사용한다.
		(a, b) -> { return a+b; }
		(a, b) -> return a+b
		
	6. 실행문에 return문만 있는 경우 return과 중괄호 '{ }'를 생략할 수 있다.
		(a, b) -> a+b
		
	- 람다식으로 변환할 수 있는 것 ==> 하나의 추상 메서드가 선언된 인터페이스(함수적 인터페이스)
	- 람다식은 함수적 인터페이스에 선언된 메서드의 반환값과 매개변수를 참고하여 작성한다.
	- 함수적 인터페이스를 나타내는 annotation ==> @FuntionalInterface
 */
public class LambdaTest1 {

	public static void main(String[] args) {
		// Runnable인터페이스는 함수적 인터페이스다.
		
		// 람다식을 사용하지 않는 경우
		Thread th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 1; i <= 10; i++) {
					System.out.println(i);
				}
			}
		});
		th1.start();
		
		// 람다식을 사용하는 경우
		Thread th2 = new Thread(
			( ) -> {
				for(int i = 1; i <= 10; i++) {
					System.out.println("람다식-" + i);
				}
			}
		);
		th2.start();
				
	}

}
