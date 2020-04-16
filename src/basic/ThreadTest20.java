package basic;

public class ThreadTest20 {

	public static void main(String[] args) {

		DataBox databox = new DataBox();
		
		ProducerThread pth = new ProducerThread(databox);
		ConsumerThread cth = new ConsumerThread(databox);
		
		pth.start();
		cth.start();
	}

}

// 데이터를 공급하는 쓰레드
class ProducerThread extends Thread {
	private DataBox dBox;

	public ProducerThread(DataBox dBox) {
		this.dBox = dBox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 3; i++) {
			dBox.setData("Data-" + i);
		}
	}
}

// 데이터를 꺼내서 사용하는 쓰레드
class ConsumerThread extends Thread {
	private DataBox dBox;

	public ConsumerThread(DataBox dBox) {
		this.dBox = dBox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 3; i++) {
			dBox.getData();
		}
	}
}

// 데이터를 공통으로 사용하는 클래스
class DataBox {
	private String data;
	
	// 데이터를 사용하는 메서드 --> Setter
	public synchronized String getData() {
		if(data == null) {	// 데이터가 없는지 검사
			try {
				wait(); 	// 데이터가 없으면 데이터가 채워질때까지 대기
			} catch (InterruptedException e) { }
		}
		// 데이터가 채워졌을 때 처리
		String returnData = data;
		System.out.println("데이터를 사용하는 쓰레드가 읽은 data: " + returnData);
		data = null; // 데이터를 사용 후에 데이터를 비운다.
		notify(); // wating pool에 기다리는 쓰레드 하나를 깨운다.
		return returnData;
	}
	
	// 데이터를 공급하는 메서드 --> Getter
	public synchronized void setData(String data) {
		if(this.data != null) { // 현재 데이터가 있는지 검사
			try {
				wait();	// 현재 데이터가 있으면 데이터가 비워질때까지 기다린다.
			} catch (InterruptedException e) { }
		}
		// 데이터가 비워지면 처리
		this.data = data;
		System.out.println("공급한 데이터: " + data);
		notify();
	}
}