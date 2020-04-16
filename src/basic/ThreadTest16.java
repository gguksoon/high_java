package basic;

public class ThreadTest16 {

	public static void main(String[] args) {
		//공통으로 사용할 객체 생성
		ShareObject sobj = new ShareObject();
		
		WorkThread th1 = new WorkThread("1번쓰레드", sobj);
		WorkThread th2 = new WorkThread("2번쓰레드", sobj);
		
		th1.start();
		th2.start();
	}

}


//처리용 쓰레드
class WorkThread extends Thread {
	private ShareObject sObj;
	
	public WorkThread(String name, ShareObject sObj) {
		super(name);		//Thread객체의 name설정
		this.sObj = sObj;
	}

	@Override
	public void run() {
		for(int i = 0; i <= 10; i++){
			sObj.add();
		}
	}
}

//공통으로 사용할 클래스
class ShareObject{
	private int sum = 0;
	
	public int getSum(){
		return sum;
	}
	
	//메서드를 한번호출할때마다 10씩 증가시켜주는 메서드
//	public synchronized void add(){ // [동기화 방법1] ==> 메서드 전체에 동기화 처리
//		int n = sum;
//		n += 10;
//		sum = n;
//		System.out.println(Thread.currentThread().getName() + "합계 : " + getSum()); 
//	}
	
	public void add(){ 
		synchronized (this) { // [동기화 방법2] ==> 등기화 블럭 이용
			int n = sum;
			n += 10;
			sum = n;
			System.out.println(Thread.currentThread().getName() + "합계 : " + getSum());
		}
	}

}
