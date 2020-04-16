package basic;

// 어노테이션, 함수적 인터페이스인것을 보증
@FunctionalInterface
public interface LambdaTestInterface01 {
	// 반환값이 없고 매개변수도 없는
	public void test();
//	public void test2(); // 어노테이션 때문에 오류가 발생(메서드는 1개만 존재)
}

@FunctionalInterface
interface LambdaTestInterface02 {
	// 반환값은 없고 매개변수가 있는 추상메서드 선언
	public void test(int a);
}

@FunctionalInterface
interface LambdaTestInterface03 {
	// 반환값이 있고 매개변수가 있는 추상메서드 선언
	public int test(int a, int b);
}