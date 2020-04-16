package a_collectionTest;

import java.util.ArrayList;
import java.util.Scanner;

/*
  	문제)	5명의 사람 이름을 입력하여 ArrayList에 저장하고,
  		이 중에 '김'씨 성의 이름을 출력하시오.
  		(단, 입력은 Scanner를 이용하여 입력받는다.)
*/

public class ArrayListTest2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> nameList = new ArrayList<String>();
		
		for(int i = 0; i < 5; i++){
			System.out.print((i + 1) + "번째 이름을 입력하세요.: ");
			nameList.add(sc.nextLine());
		}
		
		System.out.println("nameList: " + nameList);
		
		System.out.print("김씨: ");
		
		for(int i = 0; i < nameList.size(); i++){
//			if(nameList.get(i).substring(0, 1).equals("김"))
//			if(nameList.get(i).indexOf("김") == 0)
//			if(nameList.get(i).charAt(0) == '김')
			if(nameList.get(i).startsWith("김"))
				System.out.print(nameList.get(i) + " ");
		}
		
	}

}
