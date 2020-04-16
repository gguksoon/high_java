package basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {

	public static void main(String[] args) {
		// JOptionPane.showInputDialog();
		// 		==> 사용자가 데이터를 입력한 후 '확인'버튼을 누르면 입력한 데이터를 반환한다.
		//			데이터 입력 여부와 관계없이 '취소'버튼을 누르면 null을 반환한다.
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		System.out.println("입력한 값: " + str);
		
		for(int i = 10; i >= 1; i--){
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
		}
	}

}
