package basic;

public class ThreadTest03 {

	public static void main(String[] args) {

		// 쓰레드의 수행시간을 체크해 보자.
//		MyRunner rr = new MyRunner();
//		Thread th = new Thread(rr);
		
		// 위 2줄을 1줄로 요약
		Thread th = new Thread(new MyRunner());
		
		// 1970.01.01 00:00:00 이후 지난 시간(밀리세컨드<1/1000초>)이 저장됨
		long startTime = System.currentTimeMillis();
		
		// 쓰레드 실행
		th.start();
		
		try {
			// 현재 실행중인 쓰레드에서 th쓰레드가 끝날때까지 대기한다.  
			th.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		} 
		
		// 1970.01.01 00:00:00 이후 지난 시간(밀리세컨드<1/1000초>)이 저장됨
		long endTime = System.currentTimeMillis();
		
		System.out.println("실행시간: " + (endTime - startTime) / 1000.0 + "초");
		
	}

}

class MyRunner implements Runnable{

	@Override
	public void run() {
		long sum = 0;
		for(long i = 1L; i <= 1_000_000_000L; i++){
			sum += i;
		}
		System.out.println("합계: " + sum);
	}
	
}