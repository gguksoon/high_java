package basic;

public class ThreadTest04 {
	/*
	 * 	1 ~ 20억까지의 합계를 구하는데 걸리는 시간 체크하기
	 * 	전체 합계를 구하는 작업을 단독으로 했을때와 여러 쓰레드로 분할해서 작업할때의 시간을 확인해 보자.
	 */
	
	public static void main(String[] args) {
		// 단독으로 처리했을 때
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		
		long startTime = System.currentTimeMillis();
		sm.start();
		try {
			sm.join();
		} catch (InterruptedException e) { }
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("1개의 쓰레드로 처리한 실행 시간: " + (endTime - startTime) / 1000.0 + "초");
		System.out.println("\n===========================================================\n");
		
		// 여러 쓰레드가 협력해서 처리했을 경우
		SumThread[] sumArr = new SumThread[]{
				new SumThread(			  1L,   500_000_000L),
				new SumThread(	500_000_001L, 1_000_000_000L),
				new SumThread(1_000_000_001L, 1_500_000_000L),
				new SumThread(1_500_000_001L, 2_000_000_000L)
		};
		
		startTime = System.currentTimeMillis();
		for(int i = 0; i < sumArr.length; i++){
			sumArr[i].start();
		}
		
		for(int i = 0; i < sumArr.length; i++){
			try {
				sumArr[i].join();
			} catch (InterruptedException e) { }
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("4개의 쓰레드로 처리한 실행 시간: " + (endTime - startTime) / 1000.0 + "초");
		
	}

}

class SumThread extends Thread {
	private long start, end;
	
	public SumThread(long start, long end){
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i = start; i <= end; i++){
			sum += i;
		}
		System.out.println(start + "부터 " + end + "까지의 합계: " + sum);
	}
}