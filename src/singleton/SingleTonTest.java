package singleton;


public class SingleTonTest {

	public static void main(String[] args) {
		// MySingleton test1 = new MySington(); // 사용 불가
		
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();
		
		System.out.println("test2 => " + test2);
		System.out.println("test3 => " + test3);

		System.out.println("test2 == test3 => " + (test2 == test3));
		
		test2.displayTest();
	}

}
