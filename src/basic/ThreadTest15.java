package basic;

/*
 	-원주율을 계산하는 쓰레드가 있다.
 	-계산된 원주율을 출력하는 쓰레드가 있다.
 	
 	
 	-원주율을 저장하는 객체가 필요하다.
 	 이 객체를 두 쓰레드에서 공통으로 사용한다.
 */


public class ThreadTest15 {

	public static void main(String[] args) {
		ShareData sd = new ShareData();		//공통으로 사용할 객체 생성
		
		//쓰레드 객체 생성
		//생성자를 통해서 초기화하는것
		CalcPIThread cpt = new CalcPIThread(sd);
		
		//객체를 생성하고 setter를 이용하는것
		PrintPIThread ppt = new PrintPIThread(); 
		ppt.setSd(sd);
		
		cpt.start();
		ppt.start();
		
	}

}

//원주율을 관리하는 클래스(공통으로 사용될 클래스)
class ShareData{
	public double result;		// 계산된 원주율이 저장될 변수
	//volatile 캐쉬메모리를 거치지않고 바로 메모리에 접근을해서 cpu속도를 따라잡기위해서
	public volatile boolean isOK = false;		//계산이 완료되었는지를 나타내는 변수
	//volatile ==> 선언된 변수를 컴파일 최적화 대상에서 제외한다.
	//			     즉, 값이 변경되면 즉시 변수에 적용되도록 처리한다.
	
}


//원주율을 계산하는 쓰레드
class CalcPIThread extends Thread{
	// 공통으로 사용할 객체의 참조값이 저장될 변수 선언
	private ShareData sd;

	//생성자를 이용하여 공통을 사용할 객체의 참조값을 설정한다.
	public CalcPIThread(ShareData sd){
		this.sd = sd; 
	}
	
	@Override
	public void run() {
	/*
	 	원주율 =>(1/1 - 1/3 + 1/5 - 1/7 ...) * 4
	 			 0	   1    2      3   --> 몫이 홀수일떄 -1
	 	
	 */
		double sum = 0;
		for(int i = 1; i < 1_000_000_000; i+=2){
			if(i/2 % 2 == 0){
				//실수로 바꿔야함
				sum += (1.0/i);
			}else{
				sum -= (1.0/i);
			}
			
		}
		sd.result = sum * 4;
		sd.isOK = true;
	}
}


//원주율 계산이 완료되면 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread {
	ShareData sd;
	
	//setter메서드를 이용하여 공통으로 사용할 객체
	public void setSd(ShareData sd){
		this.sd = sd;
	}
	
	@Override
	public void run() {
//		while(!sd.isOK){
//			//continue는 필요없는데 쓰는거임
//			continue;
//		}
		while(true){
			if(sd.isOK){
				break;
			}
		}
		System.out.println();
		System.out.println("결과  : "  + sd.result);
		System.out.println(" PI : " + Math.PI);
	}
}
