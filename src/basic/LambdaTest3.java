package basic;

public class LambdaTest3 {

	public void testMethod(int temp) {
		final int localVar = 40;
		int kor = 100;
		
		// 람다식
		LambdaTestInterface01 lt = 
			() -> {
				// 람다식에서 지역 변수 사용
				
				// 람다식에서 지역 변수를 사용하려면 그 지역변수는 final이어야 한다.
				// jdk1.8 이상에서는 final을 붙이지 않으면 컴파일러가 자동으로 붙여준다.
				System.out.println("temp => " + temp);
				System.out.println("localVar = " + localVar);
				
			};
			
			lt.test();
	}
	
	public static void main(String[] args) {
		new LambdaTest3().testMethod(50);
	}

}
