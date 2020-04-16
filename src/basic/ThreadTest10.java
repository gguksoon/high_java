package basic;

public class ThreadTest10 {

	public static void main(String[] args) {

		StatePrintThread spt = new StatePrintThread(new TargetThread());
		spt.start();
	}

}

// TargetThread의 상태를 출력하는 쓰레드
class StatePrintThread extends Thread {
	private TargetThread targetThread;
	
	// 생성자
	public StatePrintThread(TargetThread targetThread){
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			// 쓰레드의 상태 구하기
			Thread.State state = targetThread.getState();
			System.out.println("타겟 쓰레드의 상태: " + state);
			
			// 쓰레드가 실행 전인지 확인
			if(state == Thread.State.NEW){
				targetThread.start(); // 쓰레드를 작동 시킨다.
			}
			
			// 쓰레드가 종료 상태인지 확인
			if(state == Thread.State.TERMINATED){
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) { }
			
		}
	}
	
}

// target용 쓰레드
class TargetThread extends Thread {
	
	@Override
	public void run() {
		
		// RUNNABLE상태
		for(long i = 1; i <= 20_000_000_000L; i++){	} // 시간 지연용
		
		// TIMED_WAITING상태
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) { }
		
		// RUNNABLE상태
		for(long i = 1; i <= 20_000_000_000L; i++){	} // 시간 지연용
	}
}