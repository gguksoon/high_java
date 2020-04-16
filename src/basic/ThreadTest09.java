package basic;

public class ThreadTest09 {

	public static void main(String[] args) {
		
		AutoSaveThread autoSave = new AutoSaveThread();
		
		// 데몬 쓰레드로 설정하기 (start()메서드 호출하기전에 설정)
		// 데몬 쓰레드는 메인 쓰레드가 끝나도 실행된다.
		autoSave.setDaemon(true);
		autoSave.start();
		
		try {
			for(int i = 1; i <= 20; i++){
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) { }
		
		System.out.println("메인 쓰레드 종료...");
	}

}

// 자동 저장하는 쓰레드 ==> 3초에 한번씩 자동 저장하기
class AutoSaveThread extends Thread {
	public void save(){ // 저장하기를 처리하는 메서드(현재는 print만 찍어줌)
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) { 
				break;
			}
			save();
		}
	}
}