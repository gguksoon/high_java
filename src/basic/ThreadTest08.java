package basic;

public class ThreadTest08 {

	public static void main(String[] args) {

		Thread th1 = new ThreadUpper();
		Thread th2 = new ThreadLower();
		
		// 쓰레드의 우선순위의 기본값은 5이다.
		th1.setPriority(9);
		th1.setPriority(8);
		
		System.out.println("th1의 우선순위: " + th1.getPriority());
		System.out.println("th2의 우선순위: " + th2.getPriority());
		
		th1.start();
		th2.start();
	}

}

class ThreadUpper extends Thread {
	
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++){
			System.out.println(ch);
			
			// 시간 지연을 위한 아무것도 안하는 반복문
			for(long i = 1; i <= 1000000000L; i++){	}
		}
	}
}

class ThreadLower extends Thread {
	
	@Override
	public void run() {
		for(char ch = 'a'; ch <= 'z'; ch++){
			System.out.println(ch);
			
			// 시간 지연을 위한 아무것도 안하는 반복문
			for(long i = 1; i <= 1000000000L; i++){	}
		}
	}
}