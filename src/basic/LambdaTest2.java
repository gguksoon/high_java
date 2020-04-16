package basic;

public class LambdaTest2 {

	public static void main(String[] args) {
		// 람다식을 사용하지 않았을 경우
		LambdaTestInterface01 t1 = new LambdaTestInterface01() {
			@Override
			public void test() {
				System.out.println("안녕하세요.");
			}
		};
		t1.test();
		
		// 메서드 이름이 안보이지만 test()로 실행
		LambdaTestInterface01 t2 = ( ) -> 
			{ System.out.println("람다식1-안녕하세요."); };
		t2.test();
		
		LambdaTestInterface01 t3 = 
			( ) -> System.out.println("람다식2-안녕하세요.");
		t3.test();
		
		System.out.println("-------------------------------------------------------");
		
		LambdaTestInterface02 t4 = 
			(int a) -> { 
				int result = a * 3;
				System.out.println(a + " * 3 = " + result);
			};
		t4.test(45);
		
		LambdaTestInterface02 t5 =
			a -> {
				int result = a * 5;
				System.out.println(a + " * 5 = " + result);
			};
		t5.test(66);
		
		LambdaTestInterface02 t6 = a -> System.out.println(a + " * 7 = " + (a * 7));
		t6.test(81);
		
		System.out.println("-------------------------------------------------------");
		
		LambdaTestInterface03 t7 =
			(int a, int b) -> {
				int r = a * b;
				return r;
			};
		int k1 = t7.test(10, 6);
		System.out.println("k1 = " + k1);
		
		LambdaTestInterface03 t8 =
			(x, y) -> {
				return x * y;
			};
		int k2 = t8.test(5, 9);
		System.out.println("k2 = " + k2);
		
		LambdaTestInterface03 t9 = 
			(x, y) -> x * y;
		int k3 = t9.test(10, 20);
		System.out.println("k3 = " + k3);
	}

}
