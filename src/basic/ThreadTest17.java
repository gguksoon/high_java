package basic;
/*
 * 은행의 입출금을 쓰레드로 처리하는 예제
 */
public class ThreadTest17 { // ThreadTest17가 입출금을 관리하도록 만들 객체

	private int balance; // 잔액
	
	public int getBalance(){
		return balance;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	// 입금하는 메서드
	public synchronized void deposit(int money){
		balance += money;
	}
	
	// 출금하는 메서드 (출금 성공시 true, 실패시 false)
	// 동기화 영역에서 호출하는 메서드도 동기화 처리를 해주어야 안전하다.
	public synchronized boolean withdrawal(int money){
		if(balance >= money){ // 잔액이 충분하면
			for(int i = 1; i <= 1_000_000_000; i++){ } // 시간떼우기용
			balance -= money;
			System.out.println("메서드 안에서 balance: " + balance);
			return true;
		} else { // 잔액이 부족하면
			return false;
		}
	}
	
	public static void main(String[] args) {
		// 1.7까지는 final을 붙이지 않으면 익명 구현체에서 사용할 때 에러가 발생함
		final ThreadTest17 acount = new ThreadTest17(); 
		acount.setBalance(10000);
		
		// 익명 구현체를 이용한 쓰레드 구현
		Runnable r = new Runnable() {
			@Override
			public void run() {
				boolean result = acount.withdrawal(6000);
				System.out.println("쓰레드에서 reslut: " + result + 
						", balance: " + acount.balance);
			}
		};
		//--------------------------------------------------
		
		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r);
		th1.start();
		th2.start();
	}

}
