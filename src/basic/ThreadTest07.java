package basic;

import javax.swing.JOptionPane;

/*
 * 문제)
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고,
 * 사용자는 JOptionPane.showInputDialog()메서드를 이용하여 입력 받는다.
 * 
 * 단, 입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 	   5초 안에 입력이 없으면 게임을 진 것으로 처리하고 프로그램을 종료한다.
 *     5초 안에 입력이 완료되면 승패를 출력한다.
 *     
 * 결과예시)
 * 		==  결  과   ==
 * 		컴퓨터: 가위
 * 	        당　신: 바위
 *      결　과: 당신이 이겼습니다.
 */
public class ThreadTest07 {

	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		new InputData2().start();
		new CountDown2().start();
	}

}

class InputData2 extends Thread {
	
	@Override
	public void run() {
		
		// 유저의 입력
		String userInput = "";
		do {
			userInput = JOptionPane.showInputDialog("가위/바위/보 를 입력하세요.");
		} while (!(userInput.equals("가위") || userInput.equals("바위") || userInput.equals("보")));
		ThreadTest07.inputCheck = true;
		
		// 컴퓨터의 입력(난수)
		String[] com = {"가위", "바위", "보"};
		String comInput = com[(int)(Math.random() * 3)];
		
		// 입력한 값 출력
		System.out.println("========= 결 과 =========");
		System.out.println(" 당　신: " + userInput);
		System.out.println(" 컴퓨터: " + comInput);
		System.out.print(" 결　과: ");
		
		// 결과
		if(userInput.equals(comInput)){
			System.out.println("비겼습니다.");
		} else if(userInput.equals("가위") && comInput.equals("바위")) {
			System.out.println("당신이 졌습니다.");
		} else if(userInput.equals("가위") && comInput.equals("보")) {
			System.out.println("당신이 이겼습니다.");
		} else if(userInput.equals("바위") && comInput.equals("가위")) {
			System.out.println("당신이 이겼습니다.");
		} else if(userInput.equals("바위") && comInput.equals("보")) {
			System.out.println("당신이 졌습니다.");
		} else if(userInput.equals("보") && comInput.equals("가위")) {
			System.out.println("당신이 졌습니다.");
		} else if(userInput.equals("보") && comInput.equals("바위")) {
			System.out.println("당신이 이겼습니다.");
		}
		System.out.println("=======================");
	}
}

class CountDown2 extends Thread {

	@Override
	public void run() {
		System.out.println("[카운트]");
		for(int i = 5; i >= 1; i--){
			if(ThreadTest07.inputCheck)
				return;
			System.out.println(i + " ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
		}
		
		System.out.println("당신이 졌습니다.");
		System.exit(0);
	}
}








