package basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	// 데이터 입력 여부를 확인하기 위한 변수 선언
	// 모든 쓰레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {

//		Thread th1 = new DataInput();
//		Thread th2 = new CountDown();
//		th1.start();
//		th2.start();

		new DataInput().start();
		new CountDown().start();
		
	}

}

// 데이터를 입력받는 쓰레드
class DataInput extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		System.out.println("입력값: " + str);
		ThreadTest06.inputCheck = true;
	}
}

// 카운트 다운을 진행하는 쓰레드
class CountDown extends Thread {
	@Override
	public void run() {
		for(int i = 10; i >= 1; i--){
			if(ThreadTest06.inputCheck)
				return; // 입력받는 쓰레드에서 입력을 받으면 카운트 다운 메서드를 종료
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) { }
		}
		
		// 10초 동안 입력이 없으면 종료
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}