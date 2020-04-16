package basic;

public class ThreadTest13 {

	public static void main(String[] args) {
		ThreadYield th1 = new ThreadYield("1번 쓰레드");
		ThreadYield th2 = new ThreadYield("2번 쓰레드");
		
		th1.start();
		th2.start();
		
		System.out.println("============================ 동시작업");
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) { }
		th1.work = false;
		
		System.out.println("============================ 1번 양보중");
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) { }
		th1.work = true;
		
		System.out.println("============================ 1번 재시작");
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) { }
		th1.stop = true;
		th2.stop = true;
		
	}

}

// yield()메서드 연습용 쓰레드
class ThreadYield extends Thread {
	// getter setter 만들기 귀찮아서 이렇게 함
	public boolean stop = false; // 쓰레드 종료 여부를 결정하는 변수
	public boolean work = true;	 // 특정 작업 처리 여부를 결정하는 변수
	
	public ThreadYield(String name) {
		super(name);	// Thread클래스에는 쓰레드 이름을 저장할 name변수가 선언되어 있다.
						// 쓰레드 이름 설정
	}
	
	@Override
	public void run() {
		while(!stop){
			//...
			if(work) {
				System.out.println(getName() + "작업중...");
			} else {
				System.out.println(getName() + "양보...");
				Thread.yield();
			}
			//...
		}
		System.out.println(getName() + "종료~~~");
	}
	
	
}