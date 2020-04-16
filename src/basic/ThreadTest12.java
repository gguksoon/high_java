package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * 	10마리의 말들이 경주하는 경마 프로그램 작성하기
 * 
 * 	말은 Horse라는 이름의 클래스로 구성하고
 * 	이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
 * 	그리고 이 클래스는 등수를 오름차순으로 처리할 수 있는 정렬 기능이 있다.(Comparable 구현)
 * 
 * 	경주 구간은 1 ~ 50 구간으로 되어 있다.
 * 
 * 	일정한 시간마다 말들의 현재 위치를 다음과 같이 출력한다.
 * 	예)
 * 	1번말 --------->----------------------------------------
 * 	2번말 -->-----------------------------------------------
 * 	.
 * 	.
 * 	.
 * 	10번말 ---->---------------------------------------------
 * 
 */
public class ThreadTest12 {

	// 말 들이 저장될 리스트
	static ArrayList<Horse> horse = new ArrayList<Horse>(
		Arrays.asList(new Horse("01번말"),
					  new Horse("02번말"),
					  new Horse("03번말"),
				      new Horse("04번말"),
				      new Horse("05번말"),
				      new Horse("06번말"),
				      new Horse("07번말"),
				      new Horse("08번말"),
				      new Horse("09번말"),
				      new Horse("10번말"),
				      new Horse("11번말"),
				      new Horse("12번말"),
				      new Horse("13번말"), 
				      new Horse("14번말"),
				      new Horse("15번말")
		)
	);
		
	
	
	public static void main(String[] args) {
		
		// Horse 쓰레드 실행
		for(Horse h : horse){
			h.start();
		}
		
		// HorsePosition 데몬쓰레드 실행(말의 위치를 출력하는 쓰레드)
		HorsePosition horsePosition = new HorsePosition();
		horsePosition.setDaemon(true);
		horsePosition.start();
		
		// 쓰레드들이 종료될 때 까지 대기하기 위한 join()
		for(Horse h : horse){
			try {
				h.join();
			} catch(InterruptedException e){ }
		}
		
		// Horse 쓰레드가 종료되고 나서 HorsePosition까지 
		// 정상적으로 출력한 뒤 종료하도록 기다려주는 대기시간
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) { }
		
		// 경기 결과를 출력
		System.out.println("\n==<경기　결과>==");
		Collections.sort(horse); // 말 리스트 순위별 오름차순 정렬
		for(Horse h : horse){
			System.out.println(h); // 순위 오름차순으로 출력
		}
		System.out.println("=============");
	}

}

class Horse extends Thread implements Comparable<Horse>{
	private String horseName;
	private int rank;
	private int position;
	
	// 생성자
	public Horse(String horseName) {
		this.horseName = horseName;
		this.rank = 0;
		this.position = 0;
	}
	
	@Override
	public void run() {
		
		// 말이 0~0.3초의 난수마다 1칸씩 전진
		for(int i = 0; i < 49; i++){
			position++;
			try {
				Thread.sleep((int)(Math.random() * 300 + 1)); // 1~300밀리초
			} catch (InterruptedException e) { }
		}
		
		// 말이 종점에 도착했을 때 순위를 매김
		rank = 1;
		for(Horse h : ThreadTest12.horse){
			if(h.getRank() != 0) // 다른 모든 말을 조회했을 때 0이 아니면 먼저 들어온 말이다.
				rank++;
		}
		rank--; // 본인을 제외
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int compareTo(Horse h) { // 오름차순 내부정렬 
		return new Integer(getRank()).compareTo(h.getRank());
	}

	@Override
	public String toString() {
		return (getRank() <= 9 ? "0" : "") + getRank() + "등\t" + horseName;
	}
	
}

class HorsePosition extends Thread {
	
	@Override
	public void run() {
		// 0.15초마다 갱신하여 출력해줌.
		while(true){
			// 0.15초 sleep
			try {
				Thread.sleep(150);
			} catch (Exception e) { }
			
			// 공백
			for(int i = 0; i < 40; i++){
				System.out.println();
			}
			
			// 모든 말을 출력하기 위해 리스트를 모두 탐색
			for(Horse h : ThreadTest12.horse){
				System.out.print(h.getHorseName() + " ");
				for(int i = 0; i < 50; i++){ // 현재 위치에 ">"를 찍어줌
					if(h.getPosition() == i){
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				
				if(h.getRank() != 0){ // 종점에 도착하면 등수를 찍어줌
					
					System.out.print("\t" +(h.getRank() <= 9 ? "0" : "") + h.getRank() + "등!!");
				}
				System.out.println();
			}
			
		}
	}
}























