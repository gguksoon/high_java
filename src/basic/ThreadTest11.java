package basic;

// 3개의 쓰레드가 각각 알파벳 A~Z까지 출력하는데
// 이 출력을 끝낸 순서대로 결과를 나타내는 프로그램

public class ThreadTest11 {

	// 출력을 마친 순서대로 쓰레드 이름을 저장할 변수선언
	public static String strRank = "";
	
	public static void main(String[] args) {
		DisplayCharacter[] disChars = new DisplayCharacter[]{
			new DisplayCharacter("조민수"),	
			new DisplayCharacter("김도훈"),	
			new DisplayCharacter("박은지"),	
			new DisplayCharacter("강해신"),	
		};
		
		for(DisplayCharacter dc : disChars){
			dc.start();
		}
		
		for(DisplayCharacter dc : disChars){
			try {
				dc.join();
			} catch(InterruptedException e){ }
		}
		
		System.out.println("경기 끝...");
		System.out.println("경기 결과");
		System.out.println("순위: " + strRank);
		
	}

}

// A부터 Z까지 출력하는 쓰레드
class DisplayCharacter extends Thread {
	private String name;

	// 생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++){
			System.out.println(name + "의 출력 문자: " + ch);
			try {
				// sleep()메서드의 값을 난수로 설정한다.
				// 101 ~ 500사이의 난수값으로 설정하기
				Thread.sleep((int)(Math.random() * 400 + 101));
			} catch (InterruptedException e) {
				
			}
		}
		System.out.println(name + " 출력 끝...");
		ThreadTest11.strRank += name + " ";
	}
	
	
}
