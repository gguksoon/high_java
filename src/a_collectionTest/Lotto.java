package a_collectionTest;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Lotto {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true){
			System.out.println("\n==========================");
			System.out.println(" Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println(" 1. Lotto 구입");
			System.out.println(" 2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print(" 메뉴선택: ");
			String input = sc.nextLine();
			switch(input){
				case "1": // 구입
					System.out.println("\n Lotto 구입 시작\n");
					System.out.println(" (1000원에 로또번호 하나입니다.)");
					System.out.print(" 금액 입력: ");
					int money = Integer.parseInt(sc.nextLine());
					if(money >= 101000) { // 100장 초과
						System.out.println("\n 입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
					} else if(money < 1000) { // 1장도 못살 때
						System.out.println("\n 입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
					} else { // 1~100장 살 수 있을 때
						int num = money / 1000;
						System.out.println("\n행운의 로또번호는 아래와 같습니다.");
						for(int i = 1; i <= num; i++){
							System.out.println("로또번호" + i + ": " + new Lotto().createLotto());
						}
						System.out.println("\n받은 금액은 " + money + "원이고 거스름돈은 " + (money % 1000) + "원입니다.");
					}
					break;
				case "2": // 종료
					System.out.println("\n감사합니다");
					return;
			}
		}
	}
	
	String createLotto(){
		TreeSet<Integer> lottoList = new TreeSet<Integer>();
		while(lottoList.size() < 6) // 로또 난수 생성
			lottoList.add((int)(Math.random() * 45 + 1));
		
		String lotto = ""; // 리턴값이 저장될 String
		Iterator<Integer> it = lottoList.iterator();
		while(it.hasNext()){
			lotto += it.next();
			if(it.hasNext()) // set이 마지막이 아니면 콤마를 찍어준다.
				lotto += ",";
		}
		return lotto;
	}

}
