package basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램
		
		// Thread를 사용하는 방법
		
		// 방법1
		// Thread를 상속한 class의 인스턴스를 생성한 후
		// 이 인스턴스의 start()메서드를 호출한다.
		MyThread th1 = new MyThread();
		th1.start();
		
		// 방법2
		// Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후
		// 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다.
		// 이 때 생성된 Thread의 인스턴스의 start()메서드를 호출한다.
		MyThread2 r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.start();
		
		// 방법3 ==> 익명구현체를 이용하는 방법
		Runnable r2 = new Runnable(){
			@Override
			public void run(){
				for(int i = 1; i <= 200; i++){
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch(InterruptedException e){
						
					}
				}
			}
		};
		Thread th3 = new Thread(r2);
		th3.start();
		
		System.out.println("main메서드 종료...");
	}

}

// Thread클래스를 상속하는 클래스 작성
class MyThread extends Thread {
	
	@Override
	public void run() {
		// run()메서드 안에 쓰레드에서 처리할 내용을 기술한다.
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");

			try {
				// Thread.sleep(시간);
				// ==> 주어진 시간동안 작업을 잠시 멈춘다.
				// ==> '시간'은 밀리세컨드 단위를 사용한다.
				// 즉, 1초는 1000이 된다.
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}
	}
}

// Runnable을 구현한 클래스 작성
class MyThread2 implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}
	}
}