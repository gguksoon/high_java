package singleton;
/*
	singleton 패턴 ==> 객체를 하나만 만들어지게 하는 방법
				 ==> 외부에서 new를 사용하지 않고, 동일한 객체(인스턴스)를 반환하는 클래스
				 
		- singleton Class를 구성하는 방법
		1. 자기자신의 참조값을 저장할 변수를 private static으로 선언한다.
		2. 생성자를 private로 한다.
			(이유: 외부에서 new명령으로 객체가 생성되지 못하게 하기 위해서)
		3. 자기자신 객체를 생성해서 반환하는 메서드를 만든다.
			(이 메서드도 public static으로 작성한다.
				--> 메서드명은 보통 getInstance()로 한다.)
 */

public class MySingleton {
	// 1.
	private static MySingleton single; // 변수명은 아무꺼나 괜찮음
	
	// 2. 
	private MySingleton() {
		System.out.println("싱글톤 생성자입니다.");
	}
	
	// 3.
	public static MySingleton getInstance() {
		if(single == null) {
			single = new MySingleton();
		} 
		
		return single;
	}
	
	// 기타 이 클래스로 처리할 내용들을 기술한다.
	public void displayTest() {
		System.out.println("싱글톤 객체의 일반 메서드 처리 결과");
	}
}
