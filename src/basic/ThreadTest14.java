package basic;
/*
 * 	Thread의 stop()메서드를 호출하면 쓰레드가 바로 멈춘다.
 * 	이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서 --> 자원을 계속 가지고 있을 수도 있음(lock) 
 * 	나중에 실행되는 프로그램에 영향을 줄 수 있다.
 * 	이러한 이유로 stop()메서드의 사용을 비추천한다.
 */
public class ThreadTest14 {

	public static void main(String[] args) {
		
//		ThreadStop01 th1 = new ThreadStop01();
//		th1.start();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) { }
//			
//		// 줄이 그여진 stop()메서드는 사라질 수도 있다.(비추천)
//		th1.stop(); // --> 쓰레드가 종료되면 자원정리나 쓰레드 작업완료 출력문이 출력되지 않는다.
		
		
		
		ThreadStop02 th2 = new ThreadStop02();
		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { }
		th2.interrupt();

	}
}

class ThreadStop01 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop){
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop){
			System.out.println("쓰레드 실행 중...");
		}
		System.out.println("쓰레드 작업 완료...");
		System.out.println("자원 정리...");
		System.out.println("프로그램 종료...");
	}
}

// interrupt()메서드를 이용하여 쓰레드를 멈추게 하는 예제
class ThreadStop02 extends Thread {
	@Override
	public void run() {
		
		// 방법1
//		try {
//			while(true){
//				System.out.println("실행중...");
//				Thread.sleep(1);	// 이 메서드가 실행 중일 때 interrupt()메서드를 호출하면
//									// InterruptedException이 발생한다.
//			}
//		} catch (InterruptedException e) { 
//			System.out.println("예외 발생...");
//		}
		
		// 방법2
		while(true){
			System.out.println("실행중~~");
			
			// interrupt()메서드가 호출되었는지 검사하기
			// 방법2-1
//			if(this.isInterrupted()){	// 인스턴스 객체의 메서드를 이용하여 검사
//				break;					// interrupt()메서드가 호출되면 true반환
//			}
			
			// 방법2-2
			if(Thread.interrupted()){	// Thread의 정적 메서드를 이용하여 검사
				break;					// interrupt()메서드가 호출되면 true반환
			}
		}
		
		
		System.out.println("쓰레드 종료...");
		System.out.println("자원 정리...");
		System.out.println("프로그램 종료...");
	}
	
}